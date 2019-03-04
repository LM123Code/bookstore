package com.lm.bookstore.service;

import com.lm.bookstore.dao.OrderDao;
import com.lm.bookstore.dao.OrderItemDao;
import com.lm.bookstore.model.Order;

import java.sql.SQLException;

/**
 * @author LM_Code
 * @create 2019-03-04-16:47
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    public void createOrder(Order order){
        try {
            //插入订单表
            orderDao.addOrder(order);
            //插入订单详情表
            orderItemDao.addOrderItems(order.getItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
