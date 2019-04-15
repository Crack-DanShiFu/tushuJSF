package com.library.crack.controller;

import com.library.crack.model.Books;
import com.library.crack.utils.DBUtils;
import sun.security.pkcs11.Secmod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modify_one", urlPatterns = {"/modify_one"})
public class modify_one extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.getParameter("bookName");

        String bookName = request.getParameter("bookName");
        String publicationTime = request.getParameter("publicationTime").toString();
        String bookAuthor = request.getParameter("bookAuthor");
        String bookType = request.getParameter("BookType");
        String bookPrice = request.getParameter("bookPrice");
        String bookDescription = request.getParameter("bookDescription");


        DBUtils.getInstance().update(new Books(bookName, publicationTime, bookAuthor, bookType, bookPrice, bookDescription));

        response.sendRedirect("/index.xhtml");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
