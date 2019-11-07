package com.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ncc
 * 连接 mysql数据库的工具类
 * 数据库名称:filebackstage
 * 数据库账号：root
 * 密码：123456789
 * 调试需要自行修改
 */
public class sqlConnectUtil {
    static Connection conn;

    static {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql:///qys";
            String username = "root";
            String password = "123456789";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection GetConnection() {
        return sqlConnectUtil.conn;
    }

    /*
     * 关闭数据库
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
