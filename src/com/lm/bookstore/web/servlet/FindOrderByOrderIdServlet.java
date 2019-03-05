package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.Order;
import com.lm.bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单详情的servlet
 * @author LM_Code
 * @create 2019-03-05-20:21
 */
@WebServlet("/findOrderByOrderIdServlet")
public class FindOrderByOrderIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取orderId
        String orderId = request.getParameter("orderId");
        //调用service
        OrderService orderService = new OrderService();
        Order order = orderService.findOrderByOrderId(orderId);
        //保存数据并转发
        request.setAttribute("order", order);
        request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
    }
}
