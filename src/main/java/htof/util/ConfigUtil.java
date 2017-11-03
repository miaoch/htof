package htof.util;

import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.OAuthClient;
import eleme.openapi.sdk.oauth.response.Token;
import htof.pojo.TToken;

/**
 * Created by miaoch on 2017/10/31.
 */
public class ConfigUtil {
    // 变量为true: 沙箱环境 false: 生产环境
    private static final boolean isSandbox = false;
    private static final String appKey = PropReader.get("appKey");
    private static final String appSecret = PropReader.get("appSecret");
    private static final OAuthClient client;
    private static final Config config;
    //public static Token token;
    static {
        config = new Config(isSandbox, appKey, appSecret);
        client = new OAuthClient(config);
        //token = client.getTokenInClientCredentials();启动项目不再加载token，通过读取数据库获取token
    }
    public static Config getConfig() {
        return config;
    }
    //重新获取token
    public static Token regetToken() {
        return client.getTokenInClientCredentials();
    }

}
