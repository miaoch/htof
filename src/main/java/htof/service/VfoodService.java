package htof.service;

import eleme.openapi.sdk.api.entity.product.OCategory;
import eleme.openapi.sdk.api.entity.product.OItem;
import eleme.openapi.sdk.api.entity.product.OSpec;
import eleme.openapi.sdk.api.service.ProductService;
import htof.dao.BaseDao;
import htof.dao.ShopDao;
import htof.dao.VfoodDao;
import htof.pojo.Shop;
import htof.pojo.Vfood;
import htof.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by miaoch on 2017/11/3.
 */
@Service("vfoodService")
public class VfoodService extends BaseService<Vfood> {

    @Override
    BaseDao<Vfood> getBaseDao() {
        return vfoodDao;
    }

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private VfoodDao vfoodDao;

    public void initVfood() throws Exception {
        ProductService productService = new ProductService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        List<Shop> shops = shopDao.selectAllShop();
        for (Shop shop : shops) {
            List<OCategory> list = productService.getShopCategoriesWithChildren(shop.getId());
            for (OCategory oCategory : list) {
                Map<Long, OItem> items = productService.getItemsByCategoryId(oCategory.getId());
                for (Map.Entry<Long, OItem> item : items.entrySet()) {
                    OItem oitem = item.getValue();
                    Vfood food = new Vfood();
                    food.setId(oitem.getId());
                    food.setShopId(shop.getId());
                    food.setCategoryId(oCategory.getId());
                    food.setCategoryName(oCategory.getName());
                    food.setName(oitem.getName());
                    food.setDescription(oitem.getDescription());
                    List<OSpec> ospec = oitem.getSpecs();
                    if (ospec != null && !ospec.isEmpty()) {
                        food.setCostPrice(ospec.get(0).getPrice());
                    }
                    vfoodDao.insert(food);
                }
            }
        }
    }

}
