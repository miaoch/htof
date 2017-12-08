package htof.control;

import eleme.openapi.sdk.api.entity.shop.OShop;
import eleme.openapi.sdk.api.entity.ugc.OpenapiOrderRate;
import eleme.openapi.sdk.api.exception.ServiceException;
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
import java.util.List;

/**
 * Created by miaoch on 2017/11/23.
 */
@Controller
@RequestMapping("/shop")
public class ShopControl {
    private static Logger logger = LoggerFactory.getLogger("ShopControl");
    @Autowired
    private ShopService shopService;

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

    @RequestMapping(value = "vfoodList", method = RequestMethod.GET)
    public String vfoodList(@RequestParam(value= "shopId") Long shopId, Model model) {
        model.addAttribute("shopId", shopId);
        //TODO
        return "/shop/vfoodList";
    }
}
