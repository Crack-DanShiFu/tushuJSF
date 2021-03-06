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

@WebServlet(name = "queryAll" ,urlPatterns={"/queryAll"})
public class queryAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        ArrayList<Books> resultList=DBUtils.getInstance().queryAll();
        String jsonString = JSON.toJSONString(resultList);
        PrintWriter out =null ;
        out =response.getWriter() ;
        out.write(jsonString);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
