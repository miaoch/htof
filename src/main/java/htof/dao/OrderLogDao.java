package htof.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.OrderLog;
import htof.pojo.export.OrderLogExport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
@Repository
public interface OrderLogDao extends BaseDao<OrderLog> {
    PageList<OrderLog> selectPageList(@Param("obj") OrderLog orderLog, @Param("starttime") long starttime, @Param("endtime") long endtime, PageBounds pb);

    List<OrderLogExport> selectExport(@Param("obj") OrderLog params, @Param("starttime") long starttime, @Param("endtime") long endtime);

    double getCost(@Param("orderId") String orderId);
}
