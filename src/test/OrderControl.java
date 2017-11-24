/*
import htof.pojo.export.OrderLogExport;
import htof.util.Constants;
import htof.util.DateUtil;
import htof.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

*/
/**
 * Created by miaoch on 2017/11/1.
 *//*

@Controller
@RequestMapping("/order")
public class OrderControl {
    private static Logger logger = LoggerFactory.getLogger("OrderControl");

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "export", method = RequestMethod.POST)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String[] shopIdStr = request.getParameterValues("shopIds");
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=" + DateUtil.currentDay() + ".xls");
        try {
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(orderService.getAllOrders(shopIdStr, beginDate, endDate), OrderLogExport.getTitle(), os , "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportPage(Model model) {
        model.addAttribute("shopMap", Constants.shopMap);
        return "/order/export";
    }

    @RequestMapping(value = "orderlog", method = RequestMethod.GET)
    public String orderlog(Model model) {
        model.addAttribute("shopMap", Constants.shopMap);
        return "/order/orderlog";
    }

    @RequestMapping(value = "orderlog", method = RequestMethod.POST)
    @ResponseBody
    public String orderlog(HttpServletRequest request, HttpServletResponse response) {
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String[] shopIdStr = request.getParameterValues("shopIds");
        try {
            orderService.orderLog2DB(shopIdStr, beginDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
*/
