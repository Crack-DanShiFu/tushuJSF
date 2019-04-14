package com.library.crack.utils;

import java.sql.*;

public class DBUtils {
    static DBUtils instance = null;
    private Connection conn;

    DBUtils() {
        init();
    }

    public static DBUtils getInstance() {
        if (instance == null)
            instance = new DBUtils();
        return instance;
    }

    void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://47.107.173.225:3306/tushuJSF";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet queryAll() {
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();

            String sql = "select * from books;";
            System.out.println(sql);
            rs = stat.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("bookName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


}
