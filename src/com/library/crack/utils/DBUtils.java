package com.library.crack.utils;

import com.library.crack.model.Books;

import java.sql.*;
import java.util.ArrayList;

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
            String url = "jdbc:mysql://47.107.173.225:3306/tushuJSF?characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Books> queryAll() {
        Statement stat = null;
        ResultSet rs = null;
        ArrayList<Books> booksList = new ArrayList();
        try {
            stat = conn.createStatement();
            String sql = "select * from books;";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String publicationTime = rs.getString("publicationTime").toString();
                String bookAuthor = rs.getString("bookAuthor");
                String bookType = rs.getString("BookType");
                String bookPrice = rs.getString("bookPrice");
                String bookDescription = rs.getString("bookDescription");
                booksList.add(new Books(bookName, publicationTime, bookAuthor, bookType, bookPrice, bookDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }

    public ArrayList<Books> queryOne(String bookNames) {
        Statement stat = null;
        ResultSet rs = null;
        ArrayList<Books> booksList = new ArrayList();
        try {
            stat = conn.createStatement();
            String sql = "select * from books where bookName = '" + bookNames + "'";
            System.out.println(sql);
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String publicationTime = rs.getString("publicationTime").toString();
                String bookAuthor = rs.getString("bookAuthor");
                String bookType = rs.getString("BookType");
                String bookPrice = rs.getString("bookPrice");
                String bookDescription = rs.getString("bookDescription");
                booksList.add(new Books(bookName, publicationTime, bookAuthor, bookType, bookPrice, bookDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }

    public void delete_one(String bookName) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "delete from books where bookName = '" + bookName + "'";
            System.out.println(sql);
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Books books) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "update books set ";
            sql += "publicationTime='" + books.getPublicationTime() + "',";
            sql += "bookAuthor='" + books.getBookAuthor() + "',";
            sql += "BookType='" + books.getBookType() + "',";
            sql += "bookPrice='" + books.getBookPrice() + "',";
            sql += "bookDescription='" + books.getBookDescription() + "'";
            sql += "where bookName = '" + books.getBookName() + "'";
            System.out.println(sql);
            stat.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(Books books) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "insert into books values(";
            sql +=  "'"+books.getBookName()+"',";
            sql +=  "'"+books.getPublicationTime()+"',";
            sql +=  "'"+books.getBookAuthor()+"',";
            sql +=  "'"+books.getBookType()+"',";
            sql +=  "'"+books.getBookPrice()+"',";
            sql +=  "'"+books.getBookDescription()+"'";
            sql+=")";
            System.out.println(sql);
            stat.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Books> query(String bookNames, String bookAuthors, String bookTypeSection, String bookPriceSection, String startTime, String endTime) {
        Statement stat = null;
        ResultSet rs = null;
        ArrayList<Books> booksList = new ArrayList();
        try {
            stat = conn.createStatement();
            String sql = "select * from books where 1=1 ";
            if (bookNames != "" && bookNames != null)
                sql += "and bookName like '%" + bookNames + "%'";
            if (bookAuthors != "" && bookAuthors != null)
                sql += "and bookAuthors like '%" + bookAuthors + "%'";
            if (bookTypeSection != "" && bookTypeSection != null)
                sql += "and BookType like '%" + bookTypeSection + "%'";
            if (startTime != "" && startTime != null)
                sql += "and publicationTime >= '" + startTime + "'";
            if (endTime != "" && endTime != null)
                sql += "and publicationTime <= '" + endTime + "'";
            if (bookPriceSection != "" && bookPriceSection != null) {
                String startPrice = bookPriceSection.split(" ~ ")[0];
                String endPrice = bookPriceSection.split(" ~ ")[1];
                sql += "and bookPrice between '" + startPrice + "' and '" + endPrice + "'";
            }
            System.out.println(sql);
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String publicationTime = rs.getString("publicationTime").toString();
                String bookAuthor = rs.getString("bookAuthor");
                String bookType = rs.getString("BookType");
                String bookPrice = rs.getString("bookPrice");
                String bookDescription = rs.getString("bookDescription");
                booksList.add(new Books(bookName, publicationTime, bookAuthor, bookType, bookPrice, bookDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }

}
