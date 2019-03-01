package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LM_Code
 * @create 2019-03-01-20:47
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.把参数转为Bean，model
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            System.out.println(user);
            //2.注册
            //3.返回结果
            //3.1成功---->成功页面
            //3.2失败---->注册页面
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("参数转模型失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
