package htof.pojo.export;

import java.io.Serializable;

/**
 * 导出excel专用
 * Created by miaoch on 2017/10/31.
 */
public class StatisticsVfoodExport implements Serializable {
    private String shopName;
    private String vfoodName;
    private long count;
    private double price;

    public static String[] getTitle() {
        return new String[] {
                "店铺名","菜品名","数量","成本/收入",
        };
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
