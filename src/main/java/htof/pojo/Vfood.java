package htof.pojo;

/**
 * Created by miaoch on 2017/11/9.
 */
public class Vfood {
    private long id;
    private long shopId;
    private long categoryId;//饿了么分类Id
    private String categoryName;//饿了么分类名字
    private String name;
    private String description;
    private double price;//成本价
    private double costPrice;//售价
    private long wkscategoryId;//内部商品类别，分析数据用，待用
    private String wkscategoryName;//内部商品类别，分析数据用，待用

    public Vfood(long id) {
        this.id = id;
    }
    public Vfood() {}

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getWkscategoryId() {
        return wkscategoryId;
    }

    public void setWkscategoryId(long wkscategoryId) {
        this.wkscategoryId = wkscategoryId;
    }

    public String getWkscategoryName() {
        return wkscategoryName;
    }

    public void setWkscategoryName(String wkscategoryName) {
        this.wkscategoryName = wkscategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
