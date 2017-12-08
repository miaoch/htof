package htof.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by miaoch on 2017/10/31.
 */
public class DateUtil {
    private static final String DEFAULT_SECOND = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DAY = "yyyy-MM-dd";
    private static final SimpleDateFormat DEFAULT_SECOND_SDF = new SimpleDateFormat(DEFAULT_SECOND);
    private static final SimpleDateFormat DEFAULT_DAY_SDF = new SimpleDateFormat(DEFAULT_DAY);

    public static String date2String(Date date) {
        return DEFAULT_SECOND_SDF.format(date);
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
        return StringDateAdd(dateStr, DEFAULT_DAY, day);
    }

    public static String currentDay() {
        return date2String(new Date(), DEFAULT_DAY);
    }
    public static String currentDayInSecond() {
        return date2String(new Date(), DEFAULT_SECOND);
    }

    public static long getZeroLongtime(String time) {
        try {
            Date d =  DEFAULT_DAY_SDF.parse(time);
            return d.getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCurrent(Unit unit) {
        String dateStr = currentDayInSecond();//yyyy-MM-dd HH:mm:ss
        if (unit == null) return -1;
        switch (unit) {
            default:
                return -1;
            case YEAR:
                return Integer.parseInt(dateStr.substring(0, 4));
            case MONTH:
                return Integer.parseInt(dateStr.substring(5, 7));
            case DAY:
                return Integer.parseInt(dateStr.substring(8, 10));
            case HOUR:
                return Integer.parseInt(dateStr.substring(11, 13));
            case MINUTE:
                return Integer.parseInt(dateStr.substring(14, 16));
            case SECOND:
                return Integer.parseInt(dateStr.substring(17, 19));
        }
    }
    public enum Unit {
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND
    }
}

