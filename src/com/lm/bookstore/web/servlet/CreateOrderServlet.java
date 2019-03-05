package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.Order;
import com.lm.bookstore.model.OrderItem;
import com.lm.bookstore.model.Product;
import com.lm.bookstore.model.User;
import com.lm.bookstore.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 创建订单
 * @author LM_Code
 * @create 2019-03-04-15:23
 */
@WebServlet("/createOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session的user
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.getWriter().write("非法访问");
            return;
        }
        //取购物车
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if(cart == null || cart.size() == 0){
            response.getWriter().write("购物车没有商品");
            return;
        }
        //把数据封装好
        Order order = new Order();
        try {
            //把请求参数转化为order
            BeanUtils.populate(order, request.getParameterMap());
            //补全order数据
            order.setId(UUID.randomUUID().toString());
            order.setOrderTime(new Date());
            order.setUser(user);

            //封装订单的详情【订单有n个商品】
            List<OrderItem> items = new ArrayList<OrderItem>();
            double totalPrice = 0;//总价格计数器
            for(Entry<Product, Integer> entry : cart.entrySet()){
                OrderItem item = new OrderItem();
                //设置购买的数量
                item.setBuyNum(entry.getValue());
                item.setProduct(entry.getKey());//设置商品
                item.setOrder(order);//设置此详情所属订单
                items.add(item);
                totalPrice = item.getProduct().getPrice()*item.getBuyNum() + totalPrice;
            }
            //设置总价格
            order.setMoney(totalPrice);
            //设置order中的items
            order.setItems(items);
            //插入数据库
            OrderService orderService = new OrderService();
            orderService.createOrder(order);
            //订单成功移除购物车数据
            request.getSession().removeAttribute("cart");
            //响应
            response.getWriter().write("下单成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
