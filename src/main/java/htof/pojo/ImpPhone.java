package htof.pojo;

import java.io.Serializable;

/**
 * 数据库对应pojo
 * Created by miaoch on 2017/11/2.
 */
public class ImpPhone implements Serializable {
    private String phone;//手机号
    private String name;//顾客称呼
    private String address;//顾客地址
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
