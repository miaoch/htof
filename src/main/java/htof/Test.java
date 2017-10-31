package htof;

import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.service.OrderService;
import htof.util.HtofConfig;

/**
 * Created by miaoch on 2017/10/31.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        //获取全部订单
        OrderService orderService = new OrderService(HtofConfig.config, HtofConfig.token);
        OrderList list = orderService.getAllOrders(2289642L, 1, 20, "2017-10-31");
        System.out.println(list);
    }
}
