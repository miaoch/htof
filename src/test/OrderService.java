import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.UnauthorizedException;
import htof.dao.CustomerDao;
import htof.dao.OrderLogDao;
import htof.pojo.Customer;
import htof.pojo.OrderLog;
import htof.pojo.OrderLogExport;
import htof.service.TTokenService;
import htof.util.DateUtil;
import htof.util.ConfigUtil;
import htof.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoch on 2017/11/1.
 */
@Service("orderService")
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger("OrderService");



    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private TTokenService tTokenService;

    eleme.openapi.sdk.api.service.OrderService orderService =
            new eleme.openapi.sdk.api.service.OrderService(ConfigUtil.getConfig(), tTokenService.getToken());
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
        List<OrderLogExport> export = new ArrayList<OrderLogExport>();
        int total = 0;
        do {
            try {
                list = orderService.getAllOrders(shopId, page, pageSize, date);
            } catch (UnauthorizedException e) {
                logger.info("token失效，重新获取...");
                ConfigUtil.regetToken();
                orderService = new eleme.openapi.sdk.api.service.OrderService(ConfigUtil.getConfig(), tTokenService.getToken());
                list = orderService.getAllOrders(shopId, page, pageSize, date);
            }
            data = list.getList();
            for (OOrder oOrder : data) {
                OrderLogExport model = new OrderLogExport();
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
        List<OrderLogExport> export = new ArrayList<OrderLogExport>();
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
        List<OrderLogExport> export = new ArrayList<OrderLogExport>();
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
        List<OrderLogExport> export = new ArrayList<OrderLogExport>();
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

    public void orderLog2DB(String[] shopIds, String beginDate, String endDate) throws Exception {
        if (beginDate != null && endDate != null && beginDate.compareTo(endDate) <= 0) {
            String end = beginDate;
            while (end.compareTo(endDate) <= 0) {
                if (shopIds != null) {
                    for (String shopId : shopIds) {
                        Long shopIdL = Long.parseLong(shopId);
                        int page = 1, pageSize = 50;
                        List<OOrder> data;
                        OrderList list;
                        List<OrderLogExport> export = new ArrayList<OrderLogExport>();
                        int total = 0;
                        do {
                            try {
                                list = orderService.getAllOrders(shopIdL, page, pageSize, end);
                            } catch (UnauthorizedException e) {
                                logger.info("token失效，重新获取...");
                                ConfigUtil.regetToken();
                                orderService = new eleme.openapi.sdk.api.service.OrderService(ConfigUtil.getConfig(), tTokenService.getToken());
                                list = orderService.getAllOrders(shopIdL, page, pageSize, end);
                            }
                            data = list.getList();
                            for (OOrder oOrder : data) {
                                OrderLog ol = new OrderLog();
                                ol.setShopId(shopIdL);
                                ol.setShopName(oOrder.getShopName());//商店名
                                ol.setCreatetime(oOrder.getCreatedAt().getTime());//下单时间
                                ol.setTotalPay(oOrder.getTotalPrice());//支付金额
                                ol.setDeliverFee(oOrder.getDeliverFee());//配送费
                                ol.setCustomerName(oOrder.getConsignee());//顾客名
                                ol.setCustomerAddress(oOrder.getDeliveryPoiAddress());//顾客地址
                                ol.setCustomerPhone(StringUtil.list2String(oOrder.getPhoneList()));//顾客手机号
                                Customer customer = new Customer();
                                customer.setAddress(ol.getCustomerAddress());
                                customer.setName(ol.getCustomerName());
                                customer.setLasttime(ol.getCreatetime());
                                if (oOrder.getPhoneList() != null) {
                                    for (String phone : oOrder.getPhoneList()) {
                                        customer.setPhone(phone.replaceAll("[^\\d]", ""));
                                        customerDao.saveOrUpdate(customer);
                                    }
                                }
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
                                ol.setDiscountDetails(discountDetails);
                                ol.setDiscountPrice(discount);
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
                                ol.setOrderDetails(orderDetails);
                                ol.setOrderPrice(orderPrice);
                                ol.setLunchDetails(lunchDetails);
                                ol.setLunchFee(lunchFee);
                                orderLogDao.insert(ol);
                            }
                            page++;
                        } while ((total += data.size()) < list.getTotal());
                    }
                }
                end = DateUtil.StringDateAdd(end, 1);
            }
        }
    }
}
