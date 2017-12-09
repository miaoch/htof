package htof.control;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.Customer;
import htof.pojo.OrderLog;
import htof.pojo.export.CustomerExport;
import htof.pojo.export.OrderLogExport;
import htof.service.CustomerService;
import htof.service.OrderLogService;
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
import java.util.List;

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
    private CustomerService customerService;

    @RequestMapping
    public String index() {
        return "/export/index";
    }

    @RequestMapping(value = "orderLogList", method = RequestMethod.GET)
    public String orderLogList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                               @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                               @RequestParam(value= "sort", defaultValue = "createtime.desc") String sortStr,
                               @RequestParam(value= "orderId", required = false) String orderId,
                               @RequestParam(value= "starttime", required = false) String starttime,
                               @RequestParam(value= "endtime", required = false) String endtime,
                               HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        OrderLog params = new OrderLog();
        if (StringUtil.isNotEmpty(orderId)) params.setOrderId(orderId);
        long startlongtime = 0L;
        long endlongtime = 0L;
        if (StringUtil.isNotEmpty(starttime)) startlongtime = DateUtil.getZeroLongtime(starttime);
        if (StringUtil.isNotEmpty(endtime)) endlongtime = DateUtil.getZeroLongtime(endtime) + 24 * 3600 * 1000;
        PageList<OrderLog> pageList = orderLogService.selectPageList(params, startlongtime, endlongtime, pb);
        model.addAttribute("starttime", starttime);
        model.addAttribute("endtime", endtime);
        model.addAttribute("orderId", orderId);
        model.addAttribute("list", pageList);
        model.addAttribute("page", new Page(pageList, request));
        return "/export/orderLogList";
    }

    @RequestMapping(value = "customerList", method = RequestMethod.GET)
    public String customerList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                               @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                               @RequestParam(value= "sort", defaultValue = "lasttime.desc") String sortStr,
                               @RequestParam(value= "phone", required = false) String phone,
                               @RequestParam(value= "date", required = false) String date,
                               HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        Customer params = new Customer();
        if (StringUtil.isNotEmpty(phone)) params.setPhone(phone);
        if (StringUtil.isNotEmpty(date)) params.setLasttime(DateUtil.getZeroLongtime(date));
        PageList<Customer> pageList= customerService.selectPageList(params, pb);
        model.addAttribute("phone", phone);
        model.addAttribute("date", date);
        model.addAttribute("list", pageList);
        model.addAttribute("page", new Page(pageList, request));
        return "/export/customerList";
    }

    @RequestMapping(value = "exportCustomerExcel", method = RequestMethod.GET)
    public void exportCustomerExcel(@RequestParam(value= "phone", required = false) String phone,
                               @RequestParam(value= "date", required = false) String date,
                               HttpServletResponse response) throws IOException {
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=phone.xls");
        Customer params = new Customer();
        if (StringUtil.isNotEmpty(phone)) params.setPhone(phone);
        if (StringUtil.isNotEmpty(date)) params.setLasttime(DateUtil.getZeroLongtime(date));
        List<CustomerExport> list= customerService.selectExport(params);
        try{
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(list, CustomerExport.getTitle(), os, "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "exportOrderlogExcel", method = RequestMethod.GET)
    public void exportOrderlogExcel(@RequestParam(value= "orderId", required = false) String orderId,
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
        long startlongtime = 0L;
        long endlongtime = 0L;
        if (StringUtil.isNotEmpty(starttime)) startlongtime = DateUtil.getZeroLongtime(starttime);
        if (StringUtil.isNotEmpty(endtime)) endlongtime = DateUtil.getZeroLongtime(endtime) + 24 * 3600 * 1000;
        List<OrderLogExport> list = orderLogService.selectExport(params, startlongtime, endlongtime);
        try{
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(list, OrderLogExport.getTitle(), os, "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
