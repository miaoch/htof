package htof.service;

import htof.dao.BaseDao;
import htof.dao.OrderVfoodDao;
import htof.pojo.OrderVfood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("orderVfoodService")
public class OrderVfoodService extends BaseService<OrderVfood> {
    @Autowired
    private OrderVfoodDao orderVfoodDao;

    @Override
    BaseDao<OrderVfood> getBaseDao() {
        return orderVfoodDao;
    }

}
