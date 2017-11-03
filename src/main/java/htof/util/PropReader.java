package htof.util;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by miaoch on 2017/11/1.
 */
public class PropReader {
    private static Map<String, String> proMap = new HashMap<String, String>();

    static {
        try {
            //in = PropReader.class.getClassLoader().getResourceAsStream("config.properties");
            File classes = new File(PropReader.class.getClassLoader().getResource("").toURI());
            File[] properties_array = classes.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".properties");
                }
            });
            for (File properties : properties_array) {
                String filename = properties.getName().substring(0, properties.getName().lastIndexOf("."));
                InputStream in = null;
                try {
                    in = new FileInputStream(properties);
                    Properties pro = new Properties();
                    pro.load(in);
                    for (Map.Entry entry : pro.entrySet()) {
                        String key = filename + "." + entry.getKey();
                        String value = entry.getValue().toString();
                        proMap.put(key, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return proMap.get(key) == null ? null : proMap.get(key);
    }

    public static void main(String args[]) {
        System.out.print(get("config.appKey"));
    }
}
