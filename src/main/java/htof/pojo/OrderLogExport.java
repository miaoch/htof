package htof.pojo;

import java.io.Serializable;

/**
 * 导出excel专用
 * Created by miaoch on 2017/10/31.
 */
public class OrderLogExport  implements Serializable {
    private String shopName;//商店名称
    private String orderTime;//下单时间
    private String orderDetails;//菜品详情
    private double orderPrice;//菜品价格
    private String lunchDetails;//额外消费详情
    private double lunchFee;//额外消费
    private String discountDetails;//优惠详情
    private double discountPrice;//优惠价格
    //private double red;//红包优惠
    private double deliverFee;//配送费
    private double totalPay;//支付金额
    private String customerName;//顾客称呼
    private String customerAddress;//顾客地址
    private String customerPhone;//手机号

    public static String[] getTitle() {
        return new String[] {
                "商店名称","下单时间","菜品详情","菜品价格","额外消费详情","额外消费","优惠详情","优惠价格",
                "配送费","支付金额","顾客称呼","顾客地址","手机号",
        };
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getLunchDetails() {
        return lunchDetails;
    }

    public void setLunchDetails(String lunchDetails) {
        this.lunchDetails = lunchDetails;
    }
}
