package com.library.crack.controller;

import com.alibaba.fastjson.JSON;
import com.library.crack.model.Books;
import com.library.crack.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "query", urlPatterns = {"/query"})
public class query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String bookPriceSection = request.getParameter("bookPrice_section");
        String bookTypeSection = request.getParameter("bookTypeSection");
        ArrayList<Books> resultList = DBUtils.getInstance().query(bookName, bookAuthor,bookTypeSection,bookPriceSection, startTime, endTime);
        String jsonString = JSON.toJSONString(resultList);
        PrintWriter out = null;
        out = response.getWriter();
        out.write(jsonString);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
