package htof.service;

import htof.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by miaoch on 2017/10/31.
 */
@Service("testService")
public class TestService {

    @Autowired
    private TestDao testDao;
    public String test() {
        return testDao.test();
    }
}
