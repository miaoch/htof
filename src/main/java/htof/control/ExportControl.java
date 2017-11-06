package htof.control;

import htof.service.OrderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by miaoch on 2017/11/4.
 */
@Controller
@RequestMapping("/export")
public class ExportControl {
    private static Logger logger = LoggerFactory.getLogger("ExportControl");

    @Autowired
    private OrderLogService orderLogService;
    @RequestMapping
    public String index() {
        return "/export/index";
    }

    //TODO 分页的添加
    @RequestMapping(value = "orderLogList", method = RequestMethod.GET)
    public String orderLogList(Model model) {
        model.addAttribute("list", orderLogService.selectAll());
        return "/export/orderLogList";
    }
}
