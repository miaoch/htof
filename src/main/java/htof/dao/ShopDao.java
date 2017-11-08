package htof.dao;

import htof.pojo.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Repository
public interface ShopDao extends BaseDao<Shop> {
    public List<Shop> selectAllShop();

    public String getShopNameById(long shopId);
}
