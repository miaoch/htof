package htof.pojo;

import java.io.Serializable;

/**
 * Created by miaoch on 2017/11/3.
 */
public class User  implements Serializable {
    private long id;
    private String username;
    private String password;
    private String realname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
