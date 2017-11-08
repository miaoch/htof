package htof.util;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by miaoch on 2017/11/1.
 */
public class Constants {
    public static final String CURPAGE = "1";
    public static final String PAGESIZE = "15";

    public static final HashMap<String, String> ORDERSTATUSMAP = new HashMap<String, String>();
    public static final String ORDER_STATUS_PENDING = "pending"; //未生效订单
    public static final String ORDER_STATUS_UNPROCESSED = "unprocessed"; //未处理订单
    public static final String ORDER_STATUS_REFUNDING = "refunding";//退单处理中
    public static final String ORDER_STATUS_VALID = "valid";//已处理的有效订单
    public static final String ORDER_STATUS_INVALID = "invalid";//无效订单
    public static final String ORDER_STATUS_SETTLED = "settled";//已完成订单
    static {
        ORDERSTATUSMAP.put(ORDER_STATUS_PENDING, "未生效订单");
        ORDERSTATUSMAP.put(ORDER_STATUS_UNPROCESSED, "未处理订单");
        ORDERSTATUSMAP.put(ORDER_STATUS_REFUNDING, "退单处理中");
        ORDERSTATUSMAP.put(ORDER_STATUS_VALID, "已处理的有效订单");
        ORDERSTATUSMAP.put(ORDER_STATUS_INVALID, "无效订单");
        ORDERSTATUSMAP.put(ORDER_STATUS_SETTLED, "已完成订单");
    }
}
