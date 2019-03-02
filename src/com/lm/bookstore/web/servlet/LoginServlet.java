package com.lm.bookstore.web.servlet;

import com.lm.bookstore.exception.UserException;
import com.lm.bookstore.model.User;
import com.lm.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LM_Code
 * @create 2019-03-02-14:56
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.调用service
        UserService us = new UserService();
        try {
            User user = us.login(username, password);
            //登录成功
            //把user对象保存在session中
            request.getSession().setAttribute("user", user);
            //跳转页面
            if("管理员".equals(user.getRole())){//进入管理员页面
                response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");//重定向,不会重复提交
            }else {
                //request.getRequestDispatcher("/index.jsp").forward(request, response);//请求转发跳转
                response.sendRedirect(request.getContextPath() + "/index.jsp");//重定向,不会重复提交
            }
        } catch (UserException e) {
            e.printStackTrace();
            //登录失败
            request.setAttribute("login_msg", e.getMessage());
            //跳转页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
