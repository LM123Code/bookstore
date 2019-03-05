package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.Order;
import com.lm.bookstore.model.User;
import com.lm.bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 通过用户id查找订单
 * @author LM_Code
 * @create 2019-03-04-20:54
 */
@WebServlet("/findOrderByIdServlet")
public class FindOrderByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户的id
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.getWriter().write("非法访问");
            return;
        }
        //调用service
        OrderService os = new OrderService();
        List<Order> orders = os.findOrderByUserId(user.getId());
        //存数据
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
    }
}
