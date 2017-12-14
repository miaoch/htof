package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.BaseDao;
import htof.dao.OrderLogDao;
import htof.pojo.OrderLog;
import htof.pojo.User;
import htof.pojo.export.OrderLogExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("orderLogService")
public class OrderLogService extends BaseService<OrderLog> {
    @Autowired
    private OrderLogDao orderLogDao;

    @Override
    BaseDao<OrderLog> getBaseDao() {
        return orderLogDao;
    }

    public PageList<OrderLog> selectPageList(OrderLog orderLog, long starttime, long endtime, PageBounds pb) {
        return orderLogDao.selectPageList(orderLog, starttime, endtime, pb);
    }

    public List<OrderLogExport> selectExport(OrderLog params, long starttime, long endtime) {
        return orderLogDao.selectExport(params, starttime, endtime);
    }

    public double getCost(String orderId) {
        return orderLogDao.getCost(orderId);
    }
}
