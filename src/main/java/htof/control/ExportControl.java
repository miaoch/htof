package htof.control;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.pojo.OrderLog;
import htof.service.OrderLogService;
import htof.util.Constants;
import htof.util.Page;
import htof.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String orderLogList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                               @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                               @RequestParam(value= "sort", defaultValue = "createtime.desc") String sortStr,
                               @RequestParam(value= "orderId", defaultValue = "") String orderId,
                               HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        OrderLog params = new OrderLog();
        if (StringUtil.isNotEmpty(orderId)) params.setOrderId(orderId);
        PageList<OrderLog> pageList= orderLogService.selectPageList(params, pb);
        model.addAttribute("orderId", orderId);
        model.addAttribute("list", pageList);
        model.addAttribute("page", new Page(pageList, request));
        return "/export/orderLogList";
    }
}
