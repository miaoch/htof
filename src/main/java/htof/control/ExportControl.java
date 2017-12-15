package htof.control;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.Customer;
import htof.pojo.OrderLog;
import htof.pojo.StatisticsVfood;
import htof.pojo.export.CustomerExport;
import htof.pojo.export.OrderLogExport;
import htof.pojo.export.StatisticsVfoodExport;
import htof.service.CustomerService;
import htof.service.OrderLogService;
import htof.service.ShopService;
import htof.service.StatisticsVfoodService;
import htof.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by miaoch on 2017/11/4.
 */
@Controller
@RequestMapping("/export")
public class ExportControl {
    private static Logger logger = LoggerFactory.getLogger("ExportControl");

    @Autowired
    private OrderLogService orderLogService;

    @Autowired
    private StatisticsVfoodService statisticsVfoodService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index() {
        return "/export/index";
    }

    @RequestMapping(value = "orderLogList", method = RequestMethod.GET)
    public String orderLogList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                               @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                               @RequestParam(value= "sort", defaultValue = "shopId,createtime.desc") String sortStr,
                               @RequestParam(value= "shopId", required = false) Long shopId,
                               @RequestParam(value= "orderId", required = false) String orderId,
                               @RequestParam(value= "starttime", required = false) String starttime,
                               @RequestParam(value= "endtime", required = false) String endtime,
                               HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        OrderLog params = new OrderLog();
        if (StringUtil.isEmpty(starttime) && StringUtil.isEmpty(endtime)) {
            starttime = DateUtil.currentDay();
        }//如果都为空，设个默认值
        if (StringUtil.isNotEmpty(orderId)) params.setOrderId(orderId);
        if (shopId != null && shopId > 0) params.setShopId(shopId);
        long startlongtime = 0L;
        long endlongtime = 0L;
        if (StringUtil.isNotEmpty(starttime)) startlongtime = DateUtil.getZeroLongtime(starttime);
        if (StringUtil.isNotEmpty(endtime)) endlongtime = DateUtil.getZeroLongtime(endtime) + 24 * 3600 * 1000;
        PageList<OrderLog> pageList = orderLogService.selectPageList(params, startlongtime, endlongtime, pb);
        model.addAttribute("starttime", starttime);
        model.addAttribute("endtime", endtime);
        model.addAttribute("orderId", orderId);
        model.addAttribute("list", pageList);
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopList", shopService.selectAllShop());
        for (OrderLog o : pageList) {
            try {
                o.setCost(orderLogService.getCost(o.getOrderId()));
            } catch (Exception e) {
                logger.error(o.getOrderId() + "计算成本失败");
            }
        }
        model.addAttribute("page", new Page(pageList, request));
        return "/export/orderLogList";
    }

    @RequestMapping(value = "exportOrderlogExcel", method = RequestMethod.GET)
    public void exportOrderlogExcel(@RequestParam(value= "orderId", required = false) String orderId,
                                    @RequestParam(value= "shopId", required = false) Long shopId,
                                    @RequestParam(value= "starttime", required = false) String starttime,
                                    @RequestParam(value= "endtime", required = false) String endtime,
                                    HttpServletResponse response) throws IOException {
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=order.xls");
        OrderLog params = new OrderLog();
        if (StringUtil.isNotEmpty(orderId)) params.setOrderId(orderId);
        if (shopId != null && shopId > 0) params.setShopId(shopId);
        long startlongtime = 0L;
        long endlongtime = 0L;
        if (StringUtil.isNotEmpty(starttime)) startlongtime = DateUtil.getZeroLongtime(starttime);
        if (StringUtil.isNotEmpty(endtime)) endlongtime = DateUtil.getZeroLongtime(endtime) + 24 * 3600 * 1000;
        List<OrderLogExport> list = orderLogService.selectExport(params, startlongtime, endlongtime);
        for (OrderLogExport ole : list) {
            try {
                ole.setCost(orderLogService.getCost(ole.getOrderId()));
                ole.setRealIncome(Double.parseDouble(ole.getIncome()) - ole.getCost());
            } catch (Exception e) {
                logger.error(ole.getOrderId() + "计算成本失败");
            }
        }
        try{
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(list, OrderLogExport.getTitle(), os, "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "customerList", method = RequestMethod.GET)
    public String customerList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                               @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                               @RequestParam(value= "sort", defaultValue = "lasttime.desc") String sortStr,
                               @RequestParam(value= "phone", required = false) String phone,
                               @RequestParam(value= "name", required = false) String name,
                               @RequestParam(value= "date", required = false) String date,
                               @RequestParam(value= "vfoodId", required = false) String vfoodId,
                               @RequestParam(value= "count", required = false) Long count,
                               HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        PageList<Customer> pageList;
        if (StringUtil.isEmpty(vfoodId)) {
            Customer params = new Customer();
            if (StringUtil.isNotEmpty(phone)) params.setPhone(phone);
            if (StringUtil.isNotEmpty(name)) params.setName(name);
            if (StringUtil.isNotEmpty(date)) params.setLasttime(DateUtil.getZeroLongtime(date));
            if (count != null && count != 0) params.setCount(count);
            pageList= customerService.selectPageList(params, pb);
        } else {
            Map params = new HashMap();
            if (StringUtil.isNotEmpty(phone)) params.put("phone", phone);
            if (StringUtil.isNotEmpty(name)) params.put("name", name);
            if (StringUtil.isNotEmpty(date)) params.put("lasttime", DateUtil.getZeroLongtime(date));
            if (StringUtil.isNotEmpty(vfoodId)) params.put("vfoodId", vfoodId);
            if (count != null && count != 0) params.put("count", count);
            pageList= customerService.selectFilter(params, pb);
        }
        model.addAttribute("phone", phone);
        model.addAttribute("name", name);
        model.addAttribute("date", date);
        model.addAttribute("vfoodId", vfoodId);
        model.addAttribute("count", count);
        model.addAttribute("list", pageList);
        model.addAttribute("page", new Page(pageList, request));
        return "/export/customerList";
    }

    @RequestMapping(value = "exportCustomerExcel", method = RequestMethod.GET)
    public void exportCustomerExcel(@RequestParam(value= "phone", required = false) String phone,
                               @RequestParam(value= "date", required = false) String date,
                                    @RequestParam(value= "name", required = false) String name,
                                    @RequestParam(value= "vfoodId", required = false) String vfoodId,
                                    @RequestParam(value= "count", required = false) Long count,
                               HttpServletResponse response) throws IOException {
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=phone.xls");
        List<CustomerExport> list;
        if (StringUtil.isEmpty(vfoodId)) {
            Customer params = new Customer();
            if (StringUtil.isNotEmpty(phone)) params.setPhone(phone);
            if (StringUtil.isNotEmpty(name)) params.setName(name);
            if (StringUtil.isNotEmpty(date)) params.setLasttime(DateUtil.getZeroLongtime(date));
            if (count != null && count != 0) params.setCount(count);
            list = customerService.selectExport(params);
        } else {
            Map params = new HashMap();
            if (StringUtil.isNotEmpty(phone)) params.put("phone", phone);
            if (StringUtil.isNotEmpty(name)) params.put("name", name);
            if (StringUtil.isNotEmpty(date)) params.put("lasttime", DateUtil.getZeroLongtime(date));
            if (StringUtil.isNotEmpty(vfoodId)) params.put("vfoodId", vfoodId);
            if (count != null && count != 0) params.put("count", count);
            list= customerService.selectFilterExport(params);
        }
        try{
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(list, CustomerExport.getTitle(), os, "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "statisticsVfoodList", method = RequestMethod.GET)
    public String statisticsVfoodList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                                      @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                                      @RequestParam(value= "sort", defaultValue = "count.desc") String sortStr,
                                      @RequestParam(value= "beginDate", required = false) String beginDate,
                                      @RequestParam(value= "endDate", required = false) String endDate,
                                      @RequestParam(value= "shopId", required = false) Long shopId,
                                      HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        Map params = new HashMap();
        if (StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
            beginDate = DateUtil.currentDay();
        }//如果都为空，设个默认值
        if (StringUtil.isNotEmpty(beginDate)) {
            long beginLongtime = DateUtil.getZeroLongtime(beginDate);
            params.put("beginLongtime", beginLongtime);
        }
        if (StringUtil.isNotEmpty(endDate)) {
            long endLongtime = DateUtil.getZeroLongtime(endDate);
            params.put("endLongtime", endLongtime + 24L * 3600 * 1000);
        }
        if (shopId != null && shopId != 0) {
            params.put("shopId", shopId);
        }
        PageList<StatisticsVfood> pageList= statisticsVfoodService.selectPageListByMap(params, pb);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("shopId", shopId);
        model.addAttribute("list", pageList);
        model.addAttribute("shopList", shopService.selectAllShop());
        model.addAttribute("page", new Page(pageList, request));
        return "/export/statisticsVfoodList";
    }

    @RequestMapping(value = "exportStatisticsVfoodExcel", method = RequestMethod.GET)
    public void exportStatisticsVfoodExcel(@RequestParam(value= "beginDate", required = false) String beginDate,
                                            @RequestParam(value= "endDate", required = false) String endDate,
                                            @RequestParam(value= "shopId", required = false) Long shopId,
                                            HttpServletResponse response) throws IOException {
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        if (StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
            beginDate = DateUtil.currentDay();
        }//如果都为空，设个默认值
        response.addHeader("Content-Disposition","attachment;filename="+beginDate+"~"+endDate+".xls");
        Map params = new HashMap();
        if (StringUtil.isNotEmpty(beginDate)) {
            long beginLongtime = DateUtil.getZeroLongtime(beginDate);
            params.put("beginLongtime", beginLongtime);
        }
        if (StringUtil.isNotEmpty(endDate)) {
            long endLongtime = DateUtil.getZeroLongtime(endDate);
            params.put("endLongtime", endLongtime + 24L * 3600 * 1000);
        }
        if (shopId != null && shopId != 0) {
            params.put("shopId", shopId);
        }
        List<StatisticsVfoodExport> list= statisticsVfoodService.selectExportByMap(params);
        try{
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(list, StatisticsVfoodExport.getTitle(), os, "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
