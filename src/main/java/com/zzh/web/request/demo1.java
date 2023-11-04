package com.zzh.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zzh
 * @version 1.0
 */
@WebServlet(urlPatterns = "/demo1")
public class demo1 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        System.out.println(method);//GET
        String contextPath = req.getContextPath();
        System.out.println(contextPath);// /request-demo1
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL.toString());//http://localhost:8080/request-demo1/demo1
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);// /request-demo1/demo1
        String queryString = req.getQueryString();
        System.out.println(queryString);//username=zhangsan
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletInputStream inputStream = req.getInputStream();//字节输入流
        BufferedReader reader = req.getReader();//字符输入流
        String line = reader.readLine();
        System.out.println("表单数据=" + line);
    }
}
