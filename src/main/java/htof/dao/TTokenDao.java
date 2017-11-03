package htof.dao;

import htof.pojo.TToken;
import org.springframework.stereotype.Repository;

/**
 * Created by miaoch on 2017/11/3.
 */
@Repository
public interface TTokenDao extends BaseDao<TToken> {
    public TToken getToken();
    public void updateToken(TToken obj);
}
