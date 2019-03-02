package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LM_Code
 * @create 2019-03-02-16:54
 */
@WebServlet("/myAccountServlet")
public class MyAccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        //如果已登录myAccount.jsp
        if(user != null){
            response.sendRedirect(request.getContextPath() + "/myAccount.jsp");
        }else {
            //如果未登录login.jsp
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
