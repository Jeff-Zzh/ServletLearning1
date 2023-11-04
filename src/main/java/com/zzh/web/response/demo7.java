package com.zzh.web.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zzh
 * @version 1.0
 * response响应字节数据，写字节数据（图片、音频等）
 */
@WebServlet(urlPatterns = "/demo7")
public class demo7 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.读取文件-以字节流形式
        FileInputStream fileInputStream = new FileInputStream("f://prince.png");
        //2.获取response字节输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //3.流copy
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while( (len = fileInputStream.read(buffer)) != -1){
//            outputStream.write(buffer,0,len);
//        }
        IOUtils.copy(fileInputStream,outputStream);
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
