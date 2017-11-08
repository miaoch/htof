package htof.service;

import htof.dao.ShopDao;
import htof.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("shopService")
public class ShopService {
    @Autowired
    private ShopDao shopDao;

    public List<Shop> selectAllShop() {
        return shopDao.selectAllShop();
    }
    public String getShopNameById(long shopId) {
        return shopDao.getShopNameById(shopId);
    }
}
