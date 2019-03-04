package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 结账的功能
 * @author LM_Code
 * @create 2019-03-04-14:26
 */
@WebServlet("/settleAccountServlet")
public class SettleAccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断当前浏览器是否有登陆
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            response.sendRedirect(request.getContextPath() + "/order.jsp");
        }else {
            request.setAttribute("login_msg", "请您先登录用户");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
