package htof.util;

import java.text.Format;
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

    public static String date2String(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String StringDateAdd(String dateStr, String format, int day) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            Long time = date.getTime() + day * 24L * 3600 * 1000;
            date = new Date(time);
            result = sdf.format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String StringDateAdd(String dateStr, int day) {
        return StringDateAdd(dateStr, "yyyy-MM-dd", day);
    }

    public static String currentDay() {
        return date2String(new Date(), "yyyy_MM_dd");
    }
}
