package htof.control;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by miaoch on 2017/10/30.
 */
@Controller
@RequestMapping("/test")
public class TestControl {
    private static Logger logger = LoggerFactory.getLogger("TestControl");

    @RequestMapping
    @ResponseBody
    public String test(HttpServletRequest request) {
        logger.debug(request.getParameter("requestId"));
        logger.debug(request.getParameter("type"));
        logger.debug("debug--------");
        logger.info("info--------");
        logger.error("error--------");
        JSONObject json = new JSONObject();
        try {
            json.put("message", "ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

}
