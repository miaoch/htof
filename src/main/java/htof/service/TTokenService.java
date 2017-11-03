package htof.service;

import eleme.openapi.sdk.oauth.OAuthClient;
import eleme.openapi.sdk.oauth.response.Token;
import htof.dao.TTokenDao;
import htof.pojo.TToken;
import htof.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("tTokenService")
public class TTokenService {

    @Autowired
    private TTokenDao tTokenDao;

    public Token getToken() {
        return getToken(false);
    }
    /**
     * 从数据库读取token，若失效，则重新从接口获取
     * @param reget true 强制重新获取
     * @return
     */
    public Token getToken(boolean reget) {
        Token token;
        TToken tToken = tTokenDao.getToken();
        //超时重新获取
        if (reget || tToken.getCreatetime() + tToken.getExpires() <= System.currentTimeMillis()) {
            OAuthClient client = new OAuthClient(ConfigUtil.getConfig());
            token = client.getTokenInClientCredentials();
            tToken = token2TToken(token);
            tTokenDao.updateToken(tToken);
        } else {
            token = tToken2Token(tToken);
        }
        return token;
    }

    private static TToken token2TToken(Token token) {
        TToken tToken = new TToken();
        tToken.setId(1);
        tToken.setExpires(token.getExpires());
        tToken.setRefreshToken(token.getRefreshToken());
        tToken.setTokenType(token.getTokenType());
        tToken.setAccessToken(token.getAccessToken());
        tToken.setCreatetime(System.currentTimeMillis());
        return tToken;
    }

    private static Token tToken2Token(TToken tToken) {
        Token token = new Token();
        token.setAccessToken(tToken.getAccessToken());
        token.setExpires(tToken.getExpires());
        token.setRefreshToken(tToken.getRefreshToken());
        token.setTokenType(tToken.getTokenType());
        return token;
    }
}
