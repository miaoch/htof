package htof;

import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.Model1;
import htof.util.DateUtil;
import htof.util.ExcelUtil;
import htof.util.HtofConfig;
import htof.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        //获取全部订单
        OrderService orderService = new OrderService(HtofConfig.config, HtofConfig.token);
        OrderList list = orderService.getAllOrders(2289642L, 1, 50, "2017-10-31");
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
                            } else if (oGoodsGroup.getType().equals(OOrderDetailGroupType.extra)) {//额外 包括餐盒费
                                for (OGoodsItem oGoodsItem : oGoodsItems) {
                                    lunchDetails += oGoodsItem.getName() + ": " + oGoodsItem.getPrice() + "×" + oGoodsItem.getQuantity() + "\n";
                                    lunchFee += oGoodsItem.getTotal();
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
        ExcelUtil.writeFile(Model1.getTitle(), export, "D:\\hange_file\\123.xlsx");
    }
}
