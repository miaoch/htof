package htof.service;

import htof.dao.OrderVfoodDao;
import htof.pojo.OrderVfood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("orderVfoodService")
public class OrderVfoodService {
    @Autowired
    private OrderVfoodDao orderVfoodDao;

    public int insertInBatch(List<OrderVfood> list) {
        return orderVfoodDao.insertInBatch(list);
    }
}
