package htof.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.StatisticsVfood;
import htof.pojo.export.StatisticsVfoodExport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by miaoch on 2017/11/9.
 */
@Repository
public interface StatisticsVfoodDao extends BaseDao<StatisticsVfood> {
    PageList<StatisticsVfood> selectPageListByMap(Map params, PageBounds pb);

    List<StatisticsVfoodExport> selectExportByMap(Map params);
}
