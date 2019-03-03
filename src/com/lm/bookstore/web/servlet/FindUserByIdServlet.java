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
 * @create 2019-03-02-17:22
 */
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String userid = request.getParameter("id");
        //从数据库查
        UserService us = new UserService();
        try {
            User user = us.findUserById(userid);
            /**
             * el表达式取数据顺序：page,request,session,application
             */
            //放到request
            request.setAttribute("modifyuser",user);
            //回到modifyuserinfo.jsp显示数据
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
