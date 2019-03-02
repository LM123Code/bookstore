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
import java.util.Date;
import java.util.UUID;

/**
 * @author LM_Code
 * @create 2019-03-01-20:47
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检验验证码
        //获取表单验证码
        String client_checkcode = request.getParameter("checkcode");
        String session_checkcode = (String) request.getSession().getAttribute("checkcode_session");
        if(!client_checkcode.equals(session_checkcode)){
            //验证码不一致
            request.setAttribute("checkcode_err", "验证码输入错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);//请求转发
            return;
        }
        //1.把参数转为Bean，model
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            System.out.println(user);
            //给无数据的属性赋值
            user.setActiveCode(UUID.randomUUID().toString());//激活码赋值
            user.setRole("普通用户");//用户角色赋值
            user.setRegistTime(new Date());//注册时间赋值
            System.out.println(user);
            //2.注册
            UserService us = new UserService();
            us.register(user);
            //3.返回结果
            //3.1成功---->成功页面
            request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);//请求转发
        } catch (UserException e){
            //3.2失败---->注册页面
            e.printStackTrace();
            request.setAttribute("register_err", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);//请求转发
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("参数转成模型失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
