package htof.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by miaoch on 2017/11/1.
 */
public class PropReader {
    private static Properties pro = new Properties();
    static {
        InputStream in = null;
        try {
            in = PropReader.class.getClassLoader().getResourceAsStream("config.properties");
            pro.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String get(String key) {
        return pro.get(key) == null ? null : pro.get(key).toString();
    }

    public static void main(String args[]) {
        System.out.print(get("appKey"));
    }
}
