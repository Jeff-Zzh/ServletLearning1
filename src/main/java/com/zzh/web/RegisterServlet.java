package com.zzh.web;

import com.zzh.mapper.UserMapper;
import com.zzh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @author zzh
 * @version 1.0
 */
@WebServlet(urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装要添加的用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //2.调用mapper接口的方法，根据用户名查询用户对象，如果不存在，就添加新用户user，如果存在，就返回提示信息
        //2.1获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取Mapper接口实现类
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //2.4调用Mapper接口方法
        User user1 = mapper.selectByUsername(username);

        //3.判断用户对象是否为null
        if(user1 == null){
            //用户不存在，创建用户
            mapper.add(user);
            //提交事务
            sqlSession.commit();
            //返回注册成功信息
            //获取response输出流
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<h3>注册成功<h3>");
            //释放资源
            sqlSession.close();
        }else{
            //用户存在，给出提示信息
            //获取response输出流
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("<h3>用户名已存在<h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
