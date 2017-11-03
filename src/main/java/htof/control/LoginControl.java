package htof.control;

import htof.pojo.User;
import htof.service.UserService;
import htof.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by miaoch on 2017/11/3.
 */
@Controller
public class LoginControl {
    private static Logger logger = LoggerFactory.getLogger("LoginControl");

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(String username, String password, HttpSession session, HttpServletRequest request) {
        logger.info("{} login at {}", username, DateUtil.currentDayInSecond());
        try {
            User user = userService.login(username, password);
            if (user != null) {
                session.setAttribute("user", user);
                return "index";//返回选项页面
            } else {
                request.setAttribute("error", "1");
                return "login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(String name, HttpSession session, HttpServletRequest request) throws Exception {
        request.setAttribute("error", "2");
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() throws Exception {
        return "index";
    }
}
