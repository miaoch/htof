package htof;

import htof.util.*;

/**
 * Created by miaoch on 2017/10/31.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        System.out.println(Long.parseLong(ConfigReader.get("shopId")));
    }
}
