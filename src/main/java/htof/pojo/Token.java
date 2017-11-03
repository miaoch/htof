package htof.pojo;

import java.io.Serializable;

/**
 * 重名的带T前缀
 * Created by miaoch on 2017/11/3.
 */
public class Token extends eleme.openapi.sdk.oauth.response.Token {
    private int id;
    private long createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
