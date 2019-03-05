package com.lm.bookstore.service;

import com.lm.bookstore.dao.OrderDao;
import com.lm.bookstore.dao.OrderItemDao;
import com.lm.bookstore.dao.ProductDao;
import com.lm.bookstore.model.Order;
import com.lm.bookstore.model.OrderItem;
import com.lm.bookstore.utils.ManagerThreadLocal;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-04-16:47
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private ProductDao productDao = new ProductDao();
    public void createOrder(Order order){
        try {
            ManagerThreadLocal.beginTransaction();//开启事务
            //插入订单表
            orderDao.addOrder(order);
            //插入订单详情表
            orderItemDao.addOrderItems(order.getItems());
            //减库存
            for(OrderItem item : order.getItems()){
                productDao.updateNum(item.getProduct().getId(), item.getBuyNum());
            }
            ManagerThreadLocal.commitTransaction();//结束事务
        } catch (Exception e) {
            e.printStackTrace();
            ManagerThreadLocal.rollbackTransaction();//回滚事物
        }
    }
    /**
     * 通过用户id查找所有订单
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Order> findOrderByUserId(int userId){
        try {
            return orderDao.findOrdersByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过订单Id找Order
     * @param orderId
     * @return
     */
    public Order findOrderByOrderId(String orderId){
        try {
            return orderDao.findOrderByOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
