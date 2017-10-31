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
    private static final String appKey = "MpnAEml87E";
    private static final String appSecret = "4cd4c8eb2f8b87dce64ca4cfc7e3de2e4dae9462";
    private static Token token;
    static {
        // 实例化一个配置类
        Config config = new Config(isSandbox, appKey, appSecret);
        // 使用config对象，实例化一个授权类
        OAuthClient client = new OAuthClient(config);
        // 使用授权类获取token
        Token token = client.getTokenInClientCredentials();
        HtofConfig.token = token;
    }

    //重新获取token
    public static Token regetToken() {
        // 实例化一个配置类
        Config config = new Config(isSandbox, appKey, appSecret);
        // 使用config对象，实例化一个授权类
        OAuthClient client = new OAuthClient(config);
        // 使用授权类获取token
        Token token = client.getTokenInClientCredentials();
        HtofConfig.token = token;
        return token;
    }

    //通过refresh_token重新获取token
    public static Token refreshToken() {
        //实例化一个配置类
        Config config = new Config(false, appKey, appSecret);
        //使用config对象，实例化一个授权类
        OAuthClient client = new OAuthClient(config);
        //根据refreshToken,刷新token
        Token token = client.getTokenByRefreshToken(HtofConfig.token.getRefreshToken());
        HtofConfig.token = token;
        return token;
    }

    public static void main(String args[]) {
        System.out.println(token);
        System.out.println(token.getAccessToken());
        System.out.println(token.getExpires());
        System.out.println(token.getRefreshToken());
        System.out.println(token.getTokenType());
    }
}
