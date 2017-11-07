package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.OrderLogDao;
import htof.pojo.OrderLog;
import htof.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("orderLogService")
public class OrderLogService {
    @Autowired
    private OrderLogDao orderLogDao;

    public List<OrderLog> select(OrderLog ol) {
        return orderLogDao.select(ol);
    }

    public List<OrderLog> selectAll() {
        return orderLogDao.select(new OrderLog());
    }

    public PageList<OrderLog> selectPageList(OrderLog orderLog, PageBounds pb) {
        return orderLogDao.selectPageList(orderLog, pb);
    }
}
