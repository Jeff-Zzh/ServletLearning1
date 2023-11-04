package com.zzh.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author zzh
 * @version 1.0
 */
@WebServlet(urlPatterns = "/demo1_1")
public class demo1_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET...");
        //获取请求参数通用方式
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        for (String key : keys) {
            System.out.print(key + ":");
            String[] values = parameterMap.get(key);
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST...");
        doGet(req, resp);
    }
}
