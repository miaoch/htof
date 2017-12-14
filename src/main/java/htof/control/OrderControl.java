package htof.control;

import eleme.openapi.sdk.api.entity.order.OGoodsGroup;
import eleme.openapi.sdk.api.entity.order.OGoodsItem;
import eleme.openapi.sdk.api.entity.order.OOrder;
import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.OrderVfood;
import htof.service.ShopService;
import htof.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * Created by miaoch on 2017/11/4.
 */
@Controller
@RequestMapping("/order")
public class OrderControl {

    private static Logger logger = LoggerFactory.getLogger("ExportControl");
    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("shopList", shopService.selectAllShop());
        return "/order/index";
    }

    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public String orderList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                                @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                                @RequestParam(value= "shopId") Long shopId,
                                @RequestParam(value= "date", required = false) String date,
                               HttpServletRequest request, Model model) {
        if (StringUtil.isEmpty(date)) date = DateUtil.currentDay();
        OrderService orderService = new OrderService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        OrderList list = new OrderList();
        try {
            list = orderService.getAllOrders(shopId, curPage, pageSize, date);
        } catch (ServiceException e) {
            logger.error("getAllOrders调用失败" + e);
            e.printStackTrace();
        }
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shopService.getShopNameById(shopId));
        model.addAttribute("date", date);
        model.addAttribute("list", list.getList());
        model.addAttribute("page", new Page(curPage, pageSize, list.getTotal() , request));
        return "/order/orderList";
    }

    @RequestMapping(value = "getOrder", method = RequestMethod.GET)
    public String getOrder() {
        return "/order/orderDetail";
    }

    @RequestMapping(value = "getItemsCharts", method = RequestMethod.GET, produces="text/html;charset=utf-8")
    @ResponseBody
    public String getItemsCharts(@RequestParam(value= "orderId") String orderId, Model model) {
        OrderService orderService = new OrderService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        JSONArray series = new JSONArray();
        try {
            OOrder order = orderService.getOrder(orderId);
            List<OGoodsGroup> oGoodsGroups = order.getGroups();
            if (oGoodsGroups != null) {
                for (OGoodsGroup oGoodsGroup : oGoodsGroups) {
                    List<OGoodsItem> oGoodsItems = oGoodsGroup.getItems();
                    if (oGoodsItems != null) {
                        if (oGoodsGroup.getType().equals(OOrderDetailGroupType.normal)) {//菜单
                            for (OGoodsItem oGoodsItem : oGoodsItems) {
                                JSONObject item = new JSONObject();
                                item.put("name", oGoodsItem.getName());
                                item.put("quantity", oGoodsItem.getQuantity());
                                item.put("price", oGoodsItem.getPrice());
                                JSONArray data = new JSONArray();
                                data.put(oGoodsItem.getTotal());
                                item.put("data", data);
                                series.put(item);
                            }
                        }
                    }
                }
            }
            if (order.getPackageFee() != 0) {
                JSONObject item = new JSONObject();
                item.put("name", "餐盒费");
                JSONArray data = new JSONArray();
                data.put(order.getPackageFee());
                item.put("data", data);
                item.put("quantity", 1);
                item.put("price", order.getPackageFee());
                series.put(item);
            }
            if (order.getDeliverFee() != 0 && order.getVipDeliveryFeeDiscount()==0) {
                JSONObject item = new JSONObject();
                item.put("name", "配送费");
                JSONArray data = new JSONArray();
                data.put(order.getDeliverFee());
                item.put("data", data);
                item.put("quantity", 1);
                item.put("price", order.getDeliverFee());
                series.put(item);
            }
            if (order.getHongbao() != 0) {
                JSONObject item = new JSONObject();
                item.put("name", "红包");
                JSONArray data = new JSONArray();
                data.put(order.getHongbao());
                item.put("quantity", 1);
                item.put("price", order.getHongbao());
                item.put("data", data);
                series.put(item);
            }
            if (order.getActivityTotal() != 0) {
                JSONObject item = new JSONObject();
                item.put("name", "满减活动");
                JSONArray data = new JSONArray();
                data.put(order.getActivityTotal());
                item.put("quantity", 1);
                item.put("price", order.getActivityTotal());
                item.put("data", data);
                series.put(item);
            }
            JSONObject item = new JSONObject();
            item.put("name", "饿了么收取");
            JSONArray data = new JSONArray();
            BigDecimal b1 = new BigDecimal(order.getIncome());
            BigDecimal b2 = new BigDecimal(order.getTotalPrice());
            data.put(b1.subtract(b2).setScale(2, RoundingMode.HALF_UP));
            item.put("data", data);
            item.put("quantity", 1);
            item.put("price", - 1 * order.getActivityTotal());
            series.put(item);

            item = new JSONObject();
            item.put("name", "毛收入");
            data = new JSONArray();
            data.put(order.getIncome());
            item.put("data", data);
            item.put("quantity", 1);
            item.put("price", - 1 * order.getIncome());
            series.put(item);
        } catch (ServiceException e) {
            logger.error("orderService.getOrder调用失败" + e);
            e.printStackTrace();
        }
        return series.toString();
    }
}
