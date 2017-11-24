package htof.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.Customer;
import htof.pojo.export.CustomerExport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
@Repository
public interface CustomerDao extends BaseDao<Customer> {
    List<CustomerExport> selectExport(Customer ol);
}
