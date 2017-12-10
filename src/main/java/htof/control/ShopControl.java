package htof.control;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import eleme.openapi.sdk.api.entity.product.OCategory;
import eleme.openapi.sdk.api.entity.product.OItem;
import eleme.openapi.sdk.api.entity.shop.OShop;
import eleme.openapi.sdk.api.entity.ugc.OpenapiOrderRate;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.ProductService;
import eleme.openapi.sdk.api.service.UgcService;
import htof.pojo.Vfood;
import htof.service.ShopService;
import htof.service.VfoodService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by miaoch on 2017/11/23.
 */
@Controller
@RequestMapping("/shop")
public class ShopControl {
    private static Logger logger = LoggerFactory.getLogger("ShopControl");
    @Autowired
    private ShopService shopService;

    @Autowired
    private VfoodService vfoodService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("shopList", shopService.selectAllShop());
        return "/shop/index";
    }

    @RequestMapping(value = "getShop", method = RequestMethod.GET)
    public String getShop(@RequestParam(value= "shopId") Long shopId, Model model) {
        eleme.openapi.sdk.api.service.ShopService shopService =
                new eleme.openapi.sdk.api.service.ShopService(ConfigUtil.getConfig(), ConfigUtil.getToken());
        try {
            OShop shop = shopService.getShop(shopId);
            model.addAttribute("shop", shop);
        } catch (ServiceException e) {
            logger.error("ShopService.getShop调用失败" + e);
            e.printStackTrace();
        }
        return "/shop/shopInfo";
    }

    @RequestMapping(value = "setPrice", method = RequestMethod.GET)
    public String setPrice(@RequestParam(value= "vfoodId") Long vfoodId,
                           @RequestParam(value= "shopId") Long shopId,
                           @RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                           @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                          Model model) {
        model.addAttribute("vfood", vfoodService.selectOne(new Vfood(vfoodId)));
        model.addAttribute("shopId", shopId);
        model.addAttribute("curPage", curPage);
        model.addAttribute("pageSize", pageSize);
        return "/shop/setPrice";
    }

    @RequestMapping(value = "setPrice", method = RequestMethod.POST)
    public String setPrice(@RequestParam(value= "vfoodId") Long vfoodId,
                           @RequestParam(value= "shopId") Long shopId,
                           @RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                           @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                           @RequestParam(value= "price",required = true) Double price) {
        Vfood food = new Vfood();
        food.setId(vfoodId);
        food.setPrice(price);
        vfoodService.update(food);
        return "redirect:vfoodList?curPage=" + curPage + "&pageSize=" + pageSize + "&shopId=" + shopId;
    }

    @RequestMapping(value = "vfoodList", method = RequestMethod.GET)
    public String vfoodList(@RequestParam(value = "curPage", defaultValue = Constants.CURPAGE) Integer curPage,
                            @RequestParam(value = "pageSize", defaultValue = Constants.PAGESIZE) Integer pageSize,
                            @RequestParam(value= "sort", defaultValue = "categoryId,id") String sortStr,
                            @RequestParam(value= "shopId") Long shopId,
                            HttpServletRequest request, Model model) {
        PageBounds pb = new PageBounds(curPage, pageSize, Order.formString(sortStr));
        Vfood param = new Vfood();
        param.setShopId(shopId);
        PageList<Vfood> pageList = vfoodService.selectPageList(param, pb);
        model.addAttribute("shopId", shopId);
        model.addAttribute("shopName", shopService.getShopNameById(shopId));
        model.addAttribute("list", pageList);
        model.addAttribute("page", new Page(pageList, request));
        return "/shop/vfoodList";
    }
}
