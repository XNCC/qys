package com.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ncc
 * 数据库连接工具
 * 共derby使用
 */
public class derbyConectUtil {
   static Connection conn = null;
    static{
        // 一些字符串常量定义
        String appPath = System.getProperty("user.dir");  //获取当前项目路径
        String db = appPath + File.separator + "derby_DB"+ File.separator +Options.DATABASE; //获取当前项目路径下的数据库完整路径名称
        String driver = "org.apache.derby.jdbc.EmbeddedDriver"; //derby数据库驱动
        String protocol = "jdbc:derby:"; //derby数据库连接协议
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//// 连接derby数据库
        try {
            conn = DriverManager.getConnection(protocol + db + ";create=False");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(conn);
        System.out.println(appPath);
    }
    public static  Connection GetConnection(){
        return conn;
    }
}
