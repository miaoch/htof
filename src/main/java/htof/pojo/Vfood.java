package htof.pojo;

/**
 * Created by miaoch on 2017/11/9.
 */
public class Vfood {
    private long id;
    private long shopId;
    private String name;
    private double price;//售价
    private double costPrice;//成本价
    private double category;//商品类别，分析数据用，待用

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getCategory() {
        return category;
    }

    public void setCategory(double category) {
        this.category = category;
    }
}
