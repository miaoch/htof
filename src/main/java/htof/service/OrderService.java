package htof.service;

import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.exception.UnauthorizedException;
import htof.pojo.Model1;
import htof.util.DateUtil;
import htof.util.HtofConfig;
import htof.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoch on 2017/11/1.
 */
@Service("orderService")
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger("OrderService");

    eleme.openapi.sdk.api.service.OrderService orderService =
            new eleme.openapi.sdk.api.service.OrderService(HtofConfig.config, HtofConfig.token);
    /**
     * 根据shopId号和日期获得excel导出数据
     *
     * @param shopId 2289642L
     * @param date   2017-10-31
     * @return excelList数据
     * @throws Exception 调用接口失败
     */
    private List getOrders(long shopId, String date) throws Exception {
        int page = 1, pageSize = 50;
        List<OOrder> data;
        OrderList list;
        List<Model1> export = new ArrayList<Model1>();
        int total = 0;
        do {
            try {
                list = orderService.getAllOrders(shopId, page, pageSize, date);
            } catch (UnauthorizedException e) {
                logger.info("token失效，重新获取...");
                HtofConfig.regetToken();
                orderService = new eleme.openapi.sdk.api.service.OrderService(HtofConfig.config, HtofConfig.token);
                list = orderService.getAllOrders(shopId, page, pageSize, date);
            }
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
            page++;
        } while ((total += data.size()) < list.getTotal());
        return export;
    }

    /**
     * 根据shopId号和日期获得excel导出数据
     *
     * @param shopIds shopId数组
     * @param date    2017-10-31
     * @return excelList数据
     * @throws Exception 调用接口失败
     */
    public List getAllOrders(String[] shopIds, String date) throws Exception {
        List<Model1> export = new ArrayList<Model1>();
        if (shopIds != null) {
            for (String shopId : shopIds) {
                Long shopIdL = Long.parseLong(shopId);
                export.addAll(getOrders(shopIdL, date));
            }
        }
        return export;
    }

    /**
     * 根据shopId号和日期获得excel导出数据
     *
     * @param shopIds shopId数组
     * @param dates   [2017-10-31,...]
     * @return excelList数据
     * @throws Exception 调用接口失败
     */
    public List getAllOrders(String[] shopIds, String[] dates) throws Exception {
        List<Model1> export = new ArrayList<Model1>();
        if (dates != null) {
            for (String date : dates) {
                if (shopIds != null) {
                    for (String shopId : shopIds) {
                        Long shopIdL = Long.parseLong(shopId);
                        export.addAll(getOrders(shopIdL, date));
                    }
                }
            }
        }
        return export;
    }

    /**
     * 根据shopId号和日期获得excel导出数据
     *
     * @param shopIds shopId数组
     * @param beginDate 2017-10-31
     * @param endDate 2017-10-31
     * @return excelList数据
     * @throws Exception 调用接口失败
     */
    public List getAllOrders(String[] shopIds, String beginDate, String endDate) throws Exception {
        List<Model1> export = new ArrayList<Model1>();
        if (beginDate != null && endDate != null && beginDate.compareTo(endDate) <= 0) {
            String end = beginDate;
            while (end.compareTo(endDate) <= 0) {
                if (shopIds != null) {
                    for (String shopId : shopIds) {
                        Long shopIdL = Long.parseLong(shopId);
                        export.addAll(getOrders(shopIdL, end));
                    }
                }
                end = DateUtil.StringDateAdd(end, 1);
            }
        }
        return export;
    }
}
