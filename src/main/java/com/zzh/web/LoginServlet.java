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
@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.用MyBatis完成查询
        //2.1获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取Mapper接口实现类
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //2.4调用Mapper接口方法
        User user = mapper.select(username, password);
        //2.5释放资源
        sqlSession.close();

        //设置输出格式，并获取字符输出流
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //判断user是否为null,如果不为null，则代表通过用户提交的request数据中的用户名和密码从数据库中查找到了用户对象，所以此用户存在
        if(user == null){
            //登陆失败
            writer.write("<h2>登陆失败<h2>");
        }else{
            //登陆成功
            writer.write("<h2>登陆成功<h2>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
