package htof.control;

import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.exception.UnauthorizedException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.Model1;
import htof.service.TestService;
import htof.util.DateUtil;
import htof.util.ExcelUtil;
import htof.util.HtofConfig;
import htof.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
    public String test(HttpServletRequest request) {
        logger.debug(request.getParameter("requestId"));
        logger.debug(request.getParameter("type"));
        logger.debug("debug--------");
        logger.info("info--------");
        logger.error("error--------");
        JSONObject json = new JSONObject();
        try {
            json.put("message", "ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    @RequestMapping(value = "checkDB")
    @ResponseBody
    public String test2(HttpServletRequest request) {
        return testService.test();
    }


    @RequestMapping(value = "export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter("date");
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=export.xls");
        try {
            OutputStream os = response.getOutputStream();
            ExcelUtil.write2os(getAllOrders(date), Model1.getTitle(), os , "xls");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List getAllOrders(String date) {
        //获取全部订单
        OrderService orderService;
        OrderList list = null;
        try {
            orderService = new OrderService(HtofConfig.config, HtofConfig.token);
            list = orderService.getAllOrders(2289642L, 1, 50, date);
        } catch (UnauthorizedException e) {
            logger.info("token失效，重新获取...");
            HtofConfig.regetToken();
            orderService = new OrderService(HtofConfig.config, HtofConfig.token);
            try {
                list = orderService.getAllOrders(2289642L, 1, 50, date);
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        List<OOrder> data;
        List<Model1> export = new ArrayList<Model1>();
        int total = 0;
        do {
            data = list.getList();
            for (OOrder oOrder : data) {
                Model1 model = new Model1();
                model.setShopName(oOrder.getShopName());//商店名
                model.setOrderTime(DateUtil.date2String(oOrder.getCreatedAt()));//下单时间
                model.setTotalPay(oOrder.getTotalPrice());//支付金额
                model.setDeliverFee(oOrder.getDeliverFee());//配送费
                model.setCustomerName(oOrder.getConsignee());//顾客名
                model.setCustomerAddress(oOrder.getDeliveryPoiAddress());//顾客地址
                model.setCustomerPhone(StringUtil.list2String(oOrder.getPhoneList()));//顾客手机号
                String discountDetails = "";//优惠详情
                double discount = 0;//优惠金额
                List<OActivity> oActivities = oOrder.getOrderActivities();
                if (oActivities != null) {
                    for (OActivity oActivity : oActivities) {
                        discountDetails += oActivity.getName() + " " + oActivity.getAmount() + "\n";
                        discount += oActivity.getAmount();
                    }
                    if (discountDetails.length() > 0) {
                        discountDetails = discountDetails.substring(0, discountDetails.length() - 1);//去掉不必要的回车
                    }
                }
                model.setDiscountDetails(discountDetails);
                model.setDiscountPrice(discount);
                String orderDetails = "";//菜单详情
                double orderPrice = 0;//菜单价格
                String lunchDetails = "";//额外消费详情
                double lunchFee = 0;//额外消费(餐盒费)
                List<OGoodsGroup> oGoodsGroups = oOrder.getGroups();
                if (oGoodsGroups != null) {
                    for (OGoodsGroup oGoodsGroup : oGoodsGroups) {
                        List<OGoodsItem> oGoodsItems = oGoodsGroup.getItems();
                        if (oGoodsItems != null) {
                            if (oGoodsGroup.getType().equals(OOrderDetailGroupType.normal)) {//菜单
                                for (OGoodsItem oGoodsItem : oGoodsItems) {
                                    orderDetails += oGoodsItem.getName() + ": " + oGoodsItem.getPrice() + "×" + oGoodsItem.getQuantity() + "\n";
                                    orderPrice += oGoodsItem.getTotal();
                                }
                                if (orderDetails.length() > 0) {
                                    orderDetails = orderDetails.substring(0, orderDetails.length() - 1);
                                }
                            } else if (oGoodsGroup.getType().equals(OOrderDetailGroupType.extra)) {//额外 包括餐盒费
                                for (OGoodsItem oGoodsItem : oGoodsItems) {
                                    lunchDetails += oGoodsItem.getName() + ": " + oGoodsItem.getPrice() + "×" + oGoodsItem.getQuantity() + "\n";
                                    lunchFee += oGoodsItem.getTotal();
                                }
                                if (lunchDetails.length() > 0) {
                                    lunchDetails = lunchDetails.substring(0, lunchDetails.length() - 1);
                                }
                            }
                        }
                    }
                }
                model.setOrderDetails(orderDetails);
                model.setOrderPrice(orderPrice);
                model.setLunchDetails(lunchDetails);
                model.setLunchFee(lunchFee);
                export.add(model);
            }
        } while ((total += data.size()) < list.getTotal());
        return export;
    }
}
