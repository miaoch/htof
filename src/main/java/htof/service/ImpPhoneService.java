package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.CustomerDao;
import htof.dao.ImpPhoneDao;
import htof.pojo.Customer;
import htof.pojo.ImpPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("impPhoneService")
public class ImpPhoneService {
    @Autowired
    private ImpPhoneDao impPhoneDao;

    public void saveOrUpdate(ImpPhone impPhone) {
        impPhoneDao.saveOrUpdate(impPhone);
    }
}
