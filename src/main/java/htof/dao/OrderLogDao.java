package htof.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.OrderLog;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by miaoch on 2017/10/31.
 */
@Repository
public interface OrderLogDao extends BaseDao<OrderLog> {
    PageList<OrderLog> selectPageList(OrderLog orderLog, PageBounds pb);
}
