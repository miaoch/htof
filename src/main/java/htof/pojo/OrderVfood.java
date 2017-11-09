package htof.pojo;

/**
 * Created by miaoch on 2017/11/9.
 */
public class OrderVfood {
    private String orderId;
    private long vfoodId;
    private int quantity;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getVfoodId() {
        return vfoodId;
    }

    public void setVfoodId(long vfoodId) {
        this.vfoodId = vfoodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
