package htof.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by miaoch on 2017/10/31.
 */
public class DateUtil {
    private static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String date2String(Date date) {
        return DEFAULT_SDF.format(date);
    }
}
