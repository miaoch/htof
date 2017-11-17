package htof.control;

import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.ImpPhone;
import htof.service.ImpPhoneService;
import htof.service.TestService;
import htof.task.OrderTask;
import htof.util.ConfigUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Scanner;

/**
 * Created by miaoch on 2017/10/30.
 */
@Controller
@RequestMapping("/test")
public class TestControl {
    private static Logger logger = LoggerFactory.getLogger("TestControl");

    @Autowired
    private TestService testService;

    @Autowired
    private ImpPhoneService impPhoneService;
    @RequestMapping
    @ResponseBody
    // * 暂定为推送接口
    public String test() {
        JSONObject json = new JSONObject();
        json.put("message", "ok");
        return json.toString();
    }

    @RequestMapping(value = "/getToken")
    @ResponseBody
    public String getToken() {
        OrderList ol = null;
        OrderService orderService = new OrderService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        try {
            ol = orderService.getAllOrders(150996507L, 1, 10, "2017-11-03");
        } catch (ServiceException e) {
            logger.error("session 失效，请重试");
            ConfigUtil.getToken(true);//重新获取token
            try {
                ol = orderService.getAllOrders(150996507L, 1, 10, "2017-11-03");
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
        }
        return "123";
    }

    @RequestMapping(value = "/initData")
    @ResponseBody
    public String initData(String beginDate, String endDate) {
        testService.taskCycle(beginDate, endDate);
        //testService.taskCycle();
        return true + "";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public String importData(String data) {
        String regex = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
        ImpPhone ip = new ImpPhone();
        Scanner sc = new Scanner(data);
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("送餐地址：")) {
                ip.setAddress(line.substring(5).trim());
            } else if (line.startsWith("顾客电话：")) {
                String phone = line.substring(5).trim();
                phone = phone.replaceAll("[^\\d]", "");
                if (phone.matches(regex)) {
                    ip.setPhone(phone);
                }
            } else if (line.startsWith("顾客姓名：")) {
                ip.setName(line.substring(5).trim());
            }
        }
        if (ip.getPhone() != null) {
            impPhoneService.saveOrUpdate(ip);
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importAddress() {
        return "test/import";
    }
}
