package htof.pojo;

import java.io.Serializable;

/**
 * 数据库对应pojo
 * Created by miaoch on 2017/11/2.
 */
public class OrderLog  implements Serializable {
    private long id;
    private long shopId;//商店ID
    private String shopName;//商店名称
    private long createtime;//下单时间
    private String orderDetails;//菜品详情
    private double orderPrice;//菜品价格
    private String lunchDetails;//额外消费详情
    private double lunchFee;//额外消费
    private String discountDetails;//优惠详情
    private double discountPrice;//优惠价格
    private double deliverFee;//配送费
    private double totalPay;//支付金额
    private String customerPhone;//手机号
    private String customerName;//顾客称呼
    private String customerAddress;//顾客地址
    private long userId;//饿了么的userId套用
    private String orderId;//饿了么的订单套用
    private double income;//毛利润
    private double cost;//成本

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getLunchDetails() {
        return lunchDetails;
    }

    public void setLunchDetails(String lunchDetails) {
        this.lunchDetails = lunchDetails;
    }

    public double getLunchFee() {
        return lunchFee;
    }

    public void setLunchFee(double lunchFee) {
        this.lunchFee = lunchFee;
    }

    public String getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(String discountDetails) {
        this.discountDetails = discountDetails;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getDeliverFee() {
        return deliverFee;
    }

    public void setDeliverFee(double deliverFee) {
        this.deliverFee = deliverFee;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
