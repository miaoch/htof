package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
    public PageList<OrderLog> selectPageList(OrderLog orderLog, long starttime, long endtime, PageBounds pb) {
        return orderLogDao.selectPageList(orderLog, starttime, endtime, pb);
    }

    public List<OrderLogExport> selectExport(OrderLog params, long starttime, long endtime) {
        return orderLogDao.selectExport(params, starttime, endtime);
    }
}
