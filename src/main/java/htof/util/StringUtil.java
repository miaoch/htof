package htof.util;

import java.util.List;

/**
 * Created by miaoch on 2017/10/31.
 */
public class StringUtil {

    public static String list2String(List list) {
        return list2String(list, ",");
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s);
    }
    public static String list2String(List list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o.toString());
            sb.append(delimiter);
        }
        return sb.delete(sb.length() - delimiter.length(), sb.length()).toString();
    }
}
