package htof.pojo;

import java.io.Serializable;

/**
 * Created by miaoch on 2017/12/10.
 */
public class StatisticsVfood implements Serializable {
    private long id;
    private String date;
    private long shopId;
    private String shopName;
    private long vfoodId;
    private String vfoodName;
    private long count;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public long getVfoodId() {
        return vfoodId;
    }

    public void setVfoodId(long vfoodId) {
        this.vfoodId = vfoodId;
    }

    public String getVfoodName() {
        return vfoodName;
    }

    public void setVfoodName(String vfoodName) {
        this.vfoodName = vfoodName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
