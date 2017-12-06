package test;

import htof.util.DateUtil;
import htof.util.ExcelUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {

	public static void main(String args[]) throws IOException {
	    List<Order> export = new ArrayList<Order>();
	    File file = new File("D:\\hange_file");
        File[] files = file.listFiles();
        for (File f : files) {
            String data = read(f);
            List<String> lis = new ArrayList<String>();
            int index = -1;
            while ((index = data.indexOf("<li", index + 1)) > 0) {
                int index2 = data.indexOf("</li>", index);
                String li = data.substring(index, index2 + 5);
                lis.add(li);
            }
            for (String li : lis) {
                Order order = new Order();
                Pattern p = Pattern.compile("data-phone=\"([^\"]*)\"");
                Matcher m = p.matcher(li);
                if (m.find()) {
                    order.setPhone(m.group(1));
                }//手机
                p = Pattern.compile("data-order-time=\"([^\"]*)\"");
                m = p.matcher(li);
                if (m.find()) {
                    order.setTime(DateUtil.date2String(new Date(Long.parseLong(m.group(1))*1000)));
                }//时间
                p = Pattern.compile("<strong class=\"font-16 color-333 mr10\">([^\\<]*)</strong>");
                m = p.matcher(li);
                if (m.find()) {
                    order.setName(m.group(1));
                }//顾客姓名
                String cd = "";
                p = Pattern.compile("<tr><td width=\"60%\"><div class=\"mr10\">([^\\<]*)</div></td><td width=\"15%\" class=\"font-12 color-666\"><div class=\"mt2\">([^\\<]*)</div></td><td width=\"10%\" class=\"font-12 color-666\"><div class=\"mt2\">([^\\<]*)</div></td><td width=\"15%\" class=\"text-align-r\"><div>([^\\<]*)</div></td></tr>");
                m = p.matcher(li);
                while (m.find()) {
                    cd = cd + m.group(1);
                    cd = cd + m.group(3);
                    cd = cd + "\n";
                }//菜单
                order.setDishes(cd);
                p = Pattern.compile("<strong class=\"pull-right\">￥([^\\<]*)</strong>");
                m = p.matcher(li);
                while (m.find()) {
                    order.setEarn(m.group(1));
                }//菜单
                order.setStoreName(f.getName().substring(9, f.getName().lastIndexOf(".")));
                export.add(order);
            }
        }
        ExcelUtil.writeFile(export, "D:/hange_file/111.xls");
	}

    public static String read(File file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GB2312"));
        StringBuilder result = new StringBuilder();
        String temp;
        while ((temp = in.readLine()) != null) {
            result.append(temp + "\n");
        }
        in.close();
        return result.toString();
    }
}

class Order {
	private String storeName = "";
	private String time = "";
	private String dishes = "";
	private String earn = "";
	private String phone = "";
	private String name = "";

    public String[] getTitle() {
        return new String[] {
            "店铺名",
            "下单时间",
            "菜品",
            "毛收入",
            "顾客手机",
            "顾客姓名",
        };
    }
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDishes() {
		return dishes;
	}

	public void setDishes(String dishes) {
		this.dishes = dishes;
	}

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}