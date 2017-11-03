package htof.dao;

import htof.pojo.Token;
import org.springframework.stereotype.Repository;

/**
 * Created by miaoch on 2017/11/3.
 */
@Repository
public interface TokenDao extends BaseDao<Token> {
    public Token getToken();
    public void updateToken(Token obj);
}
