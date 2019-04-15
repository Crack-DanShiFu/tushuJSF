package com.library.crack.controller;

import com.library.crack.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteOne",urlPatterns = {"/delete_one"})
public class deleteOne extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName=request.getParameter("bookName");
        System.out.println(bookName);
        DBUtils.getInstance().delete_one(bookName);
        response.sendRedirect("/index.xhtml");
    }
}
