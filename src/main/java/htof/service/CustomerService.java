package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.CustomerDao;
import htof.dao.OrderLogDao;
import htof.pojo.Customer;
import htof.pojo.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("customerService")
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<Customer> select(Customer ol) {
        return customerDao.select(ol);
    }

    public List<Customer> selectAll() {
        return customerDao.select(new Customer());
    }

    public PageList<Customer> selectPageList(Customer customer, PageBounds pb) {
        return customerDao.selectPageList(customer, pb);
    }
}
