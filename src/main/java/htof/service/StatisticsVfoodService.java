package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import eleme.openapi.sdk.api.entity.product.OCategory;
import eleme.openapi.sdk.api.entity.product.OItem;
import eleme.openapi.sdk.api.entity.product.OSpec;
import htof.dao.BaseDao;
import htof.dao.ShopDao;
import htof.dao.StatisticsVfoodDao;
import htof.dao.VfoodDao;
import htof.pojo.Customer;
import htof.pojo.Shop;
import htof.pojo.StatisticsVfood;
import htof.pojo.Vfood;
import htof.pojo.export.StatisticsVfoodExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by miaoch on 2017/11/3.
 */
@Service("statisticsVfoodService")
public class StatisticsVfoodService extends BaseService<StatisticsVfood> {

    @Override
    BaseDao<StatisticsVfood> getBaseDao() {
        return statisticsVfoodDao;
    }

    @Autowired
    private StatisticsVfoodDao statisticsVfoodDao;

    public PageList<StatisticsVfood> selectPageListByMap(Map params, PageBounds pb) {
        return statisticsVfoodDao.selectPageListByMap(params, pb);
    }

    public List<StatisticsVfoodExport> selectExportByMap(Map params) {
        return statisticsVfoodDao.selectExportByMap(params);
    }
}
