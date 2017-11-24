package htof.pojo.export;

/**
 * Created by miaoch on 2017/11/24.
 */
public class CustomerExport {
    private long userId;//饿了么的userId套用
    private String phone;//手机号
    private String name;//顾客称呼
    private String address;//顾客地址
    private String lasttime;//最后一次购买时间
    private long count;//出现次数

    public static String[] getTitle() {
        return new String[] {
                "用户ID","手机号","顾客称呼","地址","最后一次购买时间","累计消费次数"
        };
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
