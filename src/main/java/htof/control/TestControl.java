package htof.control;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.ImpPhone;
import htof.service.ImpPhoneService;
import htof.service.StatisticsVfoodService;
import htof.service.TestService;
import htof.service.VfoodService;
import htof.task.OrderTask;
import htof.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    private VfoodService vfoodService;

    @Autowired
    private StatisticsVfoodService statisticsVfoodService;

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

    @ResponseBody
    @RequestMapping(value = "/suite/create/{suiteKey}", method = {RequestMethod.POST})
    public Map<String, String> suiteCreate(
            @PathVariable("suiteKey") String suiteKey,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "timestamp", required = false) String timestamp,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody(required = false) JSONObject json
    ) {
        try {
            logger.info("钉钉套件回调接口");
            DingTalkEncryptor dingTalkEncryptor = new DingTalkEncryptor("1234567890", "0jrd22uojzuawx5ejiniuv12yi1i8ar7lh0pn9osi8z", suiteKey);
            String encryptMsg = json.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            JSONObject callbackMsgJson = JSONObject.parseObject(plainText);
            String random = callbackMsgJson.getString("Random");
            String responseEncryMsg = random;
            Map encryptedMap = dingTalkEncryptor.getEncryptedMap(responseEncryMsg, System.currentTimeMillis(), com.dingtalk.oapi.lib.aes.Utils.getRandomStr(8));
            return encryptedMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    @RequestMapping(value = "/initFood")
    @ResponseBody
    public String initFood() {
        try {
            vfoodService.initVfood();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
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
