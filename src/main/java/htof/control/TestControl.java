package htof.control;

import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.service.TestService;
import htof.task.OrderTask;
import htof.util.ConfigUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by miaoch on 2017/10/30.
 */
@Controller
@RequestMapping("/test")
public class TestControl {
    private static Logger logger = LoggerFactory.getLogger("TestControl");

    @Autowired
    private TestService testService;
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

    @RequestMapping(value = "/getDate")
    @ResponseBody
    public String resetToken() {
        testService.taskCycle();
        return true + "";
    }
}
