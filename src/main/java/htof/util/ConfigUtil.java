package htof.util;

import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.OAuthClient;
import htof.pojo.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
public class ConfigUtil {
    // 变量为true: 沙箱环境 false: 生产环境
    private static final boolean isSandbox = false;
    private static final String appKey = PropReader.get("config.appKey");
    private static final String appSecret = PropReader.get("config.appSecret");
    private static final OAuthClient client;
    private static final Config config;

    static {
        config = new Config(isSandbox, appKey, appSecret);
        client = new OAuthClient(config);
    }
    public static Config getConfig() {
        return config;
    }

    public static Token getToken() {
        return getToken(false);
    }

    /**
     * 从数据库读取token，若失效，则重新从接口获取
     *
     * @param reget true 强制重新获取
     * @return
     */
    public static Token getToken(boolean reget) {
        Token token = null;
        try {
            token = DBUtil.executeQuery("select * from t_token where id = '1'", null, Token.class).get(0);
            //超时重新获取 超时前一天重新获取
            if (reget || token.getCreatetime() + token.getExpires() * 1000 <= System.currentTimeMillis() + 24L * 3600 * 1000) {
                eleme.openapi.sdk.oauth.response.Token fToken = client.getTokenInClientCredentials();
                List<Object> params = new ArrayList<Object>();
                params.add(fToken.getAccessToken());
                params.add(fToken.getTokenType());
                params.add(fToken.getRefreshToken());
                params.add(fToken.getExpires());
                params.add(System.currentTimeMillis());
                DBUtil.excuteUpdate("update t_token set" +
                        " accessToken = ?," +
                        " tokenType = ?," +
                        " refreshToken = ?," +
                        " expires = ?," +
                        " createtime = ?" +
                        " where id = '1'", params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static void main(String args[]) {
        getToken(true);
    }
}
