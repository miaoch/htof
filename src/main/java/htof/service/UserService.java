package htof.service;

import htof.dao.UserDao;
import htof.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaoch on 2017/11/3.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users = userDao.select(user);
        return users != null && !users.isEmpty() ? users.get(0) : null;
    }
}
