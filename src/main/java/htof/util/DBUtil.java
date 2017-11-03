package htof.util;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/10/5.
 */
public class DBUtil {
    private static final String driverClass = PropReader.get("db.jdbc.driverClass");
    private static final String url = PropReader.get("db.jdbc.url");
    private static final String username = PropReader.get("db.jdbc.user");
    private static final String password = PropReader.get("db.jdbc.password");

    public DBUtil() {
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception se) {
            System.out.println("数据库连接失败！");
            se.printStackTrace();
        }
        return con;
    }

    /**
     * 释放相应的资源
     *
     * @param rs
     * @param pstmt
     * @param conn
     */
    public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法可以完成增删改所有的操作
     *
     * @param sql
     * @param params
     * @return true or false
     */
    public static boolean excuteUpdate(String sql, List<Object> params) {
        int res = 0;//受影响的行数
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);//装载sql语句
            if (params != null) {
                //加入有？占位符，在执行之前把？占位符替换掉
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return res > 0 ? true : false;
    }

    /**
     * 使用泛型方法和反射机制进行封装
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     */
    public static <T> List<T> executeQuery(String sql, List<Object> params, Class<T> cls) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> data = new ArrayList<T>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);//装载sql语句
            if (params != null) {
                //加入有？占位符，在执行之前把？占位符替换掉
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            //把查询出来的记录封装成对应的实体类对象
            ResultSetMetaData rsd = rs.getMetaData();//获得列对象,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类型
            while (rs.next()) {
                T m = cls.newInstance();
                for (int i = 0; i < rsd.getColumnCount(); i++) {
                    String col_name = rsd.getColumnName(i + 1);//获得列名
                    Object value = rs.getObject(col_name);//获得列所对应的值
                    Field field = getField(cls, col_name);
                    field.setAccessible(true);//给私有属性设置可访问权
                    field.set(m, value);//给对象的私有属性赋值
                }
                data.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return data;
    }

    private static Field getField(Class clazz, String col_name) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(col_name);
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException();
    }
}
