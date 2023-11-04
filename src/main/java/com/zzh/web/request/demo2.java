package com.zzh.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzh
 * @version 1.0
 */
@WebServlet(urlPatterns = "/demo2")
public class demo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo2");
        //一次请求转发 资源间共享数据，request携带数据，先携带数据再转发
        req.setAttribute("key1","value1");
        req.setAttribute("key2","value2");
        //请求转发
        req.getRequestDispatcher("/demo3").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
