package htof.pojo;

/**
 * 数据库对应pojo
 * Created by miaoch on 2017/11/2.
 */
public class Customer {
    private String phone;//手机号
    private String name;//顾客称呼
    private String address;//顾客地址
    private long lasttime;//最后一次购买时间
    private long count;//出现次数

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
