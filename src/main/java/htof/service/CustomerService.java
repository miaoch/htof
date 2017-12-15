package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.BaseDao;
import htof.dao.CustomerDao;
import htof.pojo.Customer;
import htof.pojo.export.CustomerExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("customerService")
public class CustomerService extends BaseService<Customer> {
    @Autowired
    private CustomerDao customerDao;

    @Override
    BaseDao<Customer> getBaseDao() {
        return customerDao;
    }

    public List<CustomerExport> selectExport(Customer ol) {
        return customerDao.selectExport(ol);
    }

    public PageList<Customer> selectFilter(Map obj, PageBounds pg) {
        return customerDao.selectFilter(obj, pg);
    }

    public List<CustomerExport> selectFilterExport(Map params) {
        return customerDao.selectFilterExport(params);
    }
}
