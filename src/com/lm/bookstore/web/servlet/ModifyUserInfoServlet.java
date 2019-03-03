package com.lm.bookstore.web.servlet;

import com.lm.bookstore.exception.UserException;
import com.lm.bookstore.model.User;
import com.lm.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author LM_Code
 * @create 2019-03-02-18:15
 */
@WebServlet("/modifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=utf-8");
        //1.获取表单参数
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            System.out.println(user);
            //2.修改
            UserService us = new UserService();
            us.modifyUserInfo(user);
            //3.跳转页面
            response.sendRedirect(request.getContextPath() + "/modifyUserInfoSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
