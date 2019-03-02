package com.lm.bookstore.web.servlet;

import com.lm.bookstore.exception.UserException;
import com.lm.bookstore.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据激活码激活用户的servlet
 * @author LM_Code
 * @create 2019-03-02-11:42
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取激活码参数
        String activeCode = request.getParameter("activeCode");
        //激活
        UserService us = new UserService();
        try {
            us.activeUser(activeCode);
            response.getWriter().write("激活成功");//将成功结果输出在次页面
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());//将失败结果输出到此网页
        }
    }
}
