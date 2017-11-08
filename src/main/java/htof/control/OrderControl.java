package htof.control;

import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.service.ShopService;
import htof.util.*;
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
@RequestMapping("/order")
public class OrderControl {

    private static Logger logger = LoggerFactory.getLogger("ExportControl");
    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("shopList", shopService.selectAllShop());
        return "/order/index";
    }

    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public String orderList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                                @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                                @RequestParam(value= "shopId") Long shopId,
                                @RequestParam(value= "date", required = false) String date,
                               HttpServletRequest request, Model model) {
        if (StringUtil.isEmpty(date)) date = DateUtil.currentDay();
        OrderService orderService = new OrderService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        OrderList list = new OrderList();
        try {
            list = orderService.getAllOrders(shopId, curPage, pageSize, date);
        } catch (ServiceException e) {
            logger.error("getAllOrders调用失败" + e);
            e.printStackTrace();
        }
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shopService.getShopNameById(shopId));
        model.addAttribute("date", date);
        model.addAttribute("list", list.getList());
        model.addAttribute("page", new Page(curPage, pageSize, list.getTotal() , request));
        return "/order/orderList";
    }
}
