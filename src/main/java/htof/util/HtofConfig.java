package htof.util;

import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.OAuthClient;
import eleme.openapi.sdk.oauth.response.Token;

/**
 * Created by miaoch on 2017/10/31.
 */
public class HtofConfig {
    // 变量为true: 沙箱环境 false: 生产环境
    private static final boolean isSandbox = false;
    private static final String appKey = ConfigReader.get("appKey");
    private static final String appSecret = ConfigReader.get("appSecret");
    private static final OAuthClient client;
    public static final Config config;
    public static Token token;
    static {
        config = new Config(isSandbox, appKey, appSecret);
        client = new OAuthClient(config);
        token = client.getTokenInClientCredentials();
    }

    //重新获取token
    public static Token regetToken() {

        return token = client.getTokenInClientCredentials();
    }

    //通过refresh_token重新获取token
    public static Token refreshToken() {
        //根据refreshToken,刷新token
        return token = client.getTokenByRefreshToken(HtofConfig.token.getRefreshToken());
    }

    public static void main(String args[]) {
        System.out.println(token);
        System.out.println(token.getAccessToken());
        System.out.println(token.getExpires());
        System.out.println(token.getRefreshToken());
        System.out.println(token.getTokenType());
    }
}
