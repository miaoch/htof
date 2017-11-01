package htof;

import eleme.openapi.sdk.api.entity.order.*;
import eleme.openapi.sdk.api.enumeration.order.OOrderDetailGroupType;
import eleme.openapi.sdk.api.exception.UnauthorizedException;
import eleme.openapi.sdk.api.service.OrderService;
import htof.pojo.Model1;
import htof.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        System.out.println(Long.parseLong(ConfigReader.get("shopId")));
    }
}
