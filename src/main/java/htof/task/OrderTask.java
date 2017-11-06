package htof.task;

import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.exception.UnauthorizedException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.dao.CustomerDao;
import htof.dao.OrderLogDao;
import htof.dao.ShopDao;
import htof.pojo.Customer;
import htof.pojo.OrderLog;
import htof.pojo.Shop;
import htof.util.ConfigUtil;
import htof.util.DateUtil;
import htof.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miaoch on 2017/11/4.
 */
@Component
public class OrderTask {
    private static Logger logger = LoggerFactory.getLogger("OrderService");

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderLogDao orderLogDao;
    @Autowired
    private ShopDao shopDao;

    @Scheduled(cron = "0 30 23 * * ? ") //每天23.30执行一次
    public void taskCycle() {
        String date = DateUtil.date2String(new Date(), "yyyy-MM-dd");
        OrderService orderService = new OrderService(ConfigUtil.getConfig(), ConfigUtil.getToken(true));//每日由该接口重新生成session
        statistics(orderService, date);
    }

    /**
     * @param date 2017-10-13
     * @return
     */
    public List<OOrder> getAllOrdersByDate(OrderService orderService, String date) {
        List<Shop> shops = shopDao.selectAllShop();
        List<OOrder> result = new ArrayList<OOrder>();
        if (shops != null) {
            for (Shop shop : shops) {
                try {
                    int total = 0;
                    List<OOrder> data;
                    OrderList list;
                    int page = 1, pageSize = 50;
                    do {
                        list = orderService.getAllOrders(shop.getId(), page, pageSize, date);
                        data = list.getList();
                        result.addAll(data);
                        page++;
                    } while ((total += data.size()) < list.getTotal());
                } catch (UnauthorizedException e) {
                    logger.error("统计token失效，重新获取..." + e);
                    ConfigUtil.getToken(true);
                } catch (ServiceException e) {
                    logger.error("统计接口请求失败..." + e);
                }
            }
        }
        return result;
    }

    public void statistics(OrderService orderService, String date) {
        List<OOrder> result = getAllOrdersByDate(orderService, date);
        if (result != null) {
            for (OOrder oOrder : result) {
                OrderLog ol = new OrderLog();
                ol.setOrderId(oOrder.getId());
                ol.setShopId(oOrder.getShopId());
                ol.setShopName(oOrder.getShopName());//商店名
                ol.setCreatetime(oOrder.getCreatedAt().getTime());//下单时间
                ol.setTotalPay(oOrder.getTotalPrice());//支付金额
                ol.setDeliverFee(oOrder.getDeliverFee());//配送费
                ol.setCustomerName(oOrder.getConsignee());//顾客名
                ol.setCustomerAddress(oOrder.getDeliveryPoiAddress());//顾客地址
                ol.setCustomerPhone(StringUtil.list2String(oOrder.getPhoneList()));//顾客手机号
                ol.setUserId(oOrder.getUserId());//顾客Id
                Customer customer = new Customer();
                customer.setAddress(ol.getCustomerAddress());
                customer.setName(ol.getCustomerName());
                customer.setLasttime(ol.getCreatetime());
                customer.setUserId(oOrder.getUserId());
                String regex = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
                if (oOrder.getPhoneList() != null) {
                    for (String phone : oOrder.getPhoneList()) {
                        phone = phone.replaceAll("[^\\d]", "");
                        if (phone.matches(regex)) {
                            customer.setPhone(phone);
                            customerDao.saveOrUpdate(customer);
                        }
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
        }
    }
}