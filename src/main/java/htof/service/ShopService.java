package htof.service;

import htof.dao.BaseDao;
import htof.dao.ShopDao;
import htof.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("shopService")
public class ShopService extends BaseService<Shop> {
    @Autowired
    private ShopDao shopDao;

    @Override
    BaseDao<Shop> getBaseDao() {
        return shopDao;
    }

    public List<Shop> selectAllShop() {
        return shopDao.selectAllShop();
    }
    public String getShopNameById(long shopId) {
        return shopDao.getShopNameById(shopId);
    }

}
