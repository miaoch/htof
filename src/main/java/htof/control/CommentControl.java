package htof.control;

import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.entity.ugc.OpenapiOrderRate;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import eleme.openapi.sdk.api.service.UgcService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by miaoch on 2017/11/23.
 */
@Controller
@RequestMapping("/comment")
public class CommentControl {
    private static Logger logger = LoggerFactory.getLogger("CommentControl");
    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("shopList", shopService.selectAllShop());
        return "/comment/index";
    }

    @RequestMapping(value = "commentList", method = RequestMethod.GET)
    public String orderList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                            @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                            @RequestParam(value= "shopId") Long shopId,
                            @RequestParam(value= "date", required = false) String date,
                            HttpServletRequest request, Model model) {
        if (StringUtil.isEmpty(date)) date = DateUtil.currentDay();
        String enddate = DateUtil.StringDateAdd(date, +1);
        UgcService ugcService = new UgcService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        List<OpenapiOrderRate> list = null;
        try {
            list = ugcService.getOrderRatesByShopId(shopId.toString(), date + "T00:00:00", enddate + "T00:00:00", curPage, pageSize);
        } catch (ServiceException e) {
            logger.error("ugcService.getOrderRatesByShopId调用失败" + e);
            e.printStackTrace();
        }
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shopService.getShopNameById(shopId));
        model.addAttribute("date", date);
        model.addAttribute("list", list);
        model.addAttribute("page", new Page(curPage, pageSize, list.size() , request));
        return "/comment/commentList";
    }
}
