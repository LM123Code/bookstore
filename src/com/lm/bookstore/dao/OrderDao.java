package com.lm.bookstore.dao;

import com.lm.bookstore.model.Order;
import com.lm.bookstore.model.OrderItem;
import com.lm.bookstore.model.Product;
import com.lm.bookstore.utils.C3P0Utils;
import com.lm.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-04-16:48
 */
public class OrderDao {
    public void addOrder(Order order) throws SQLException {
        //编写SQL语句
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(order.getId());
        params.add(order.getMoney());
        params.add(order.getReceiverAddress());
        params.add(order.getReceiverName());
        params.add(order.getReceiverPhone());
        params.add(order.getPayState());
        params.add(order.getOrderTime());
        params.add(order.getUser().getId());

        /*QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
        qu.update(sql, params.toArray());*/
        //事务优化
        QueryRunner queryRunner = new QueryRunner();//未绑定数据源
        queryRunner.update(ManagerThreadLocal.getConnection(), sql, params.toArray());//绑定数据源
    }

    /**
     * 通过用户id查找所有订单
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Order> findOrdersByUserId(int userId) throws SQLException {
        String sql = "select * from orders where user_id = ?";
        QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
        return qu.query(sql, new BeanListHandler<Order>(Order.class), userId);
    }

    /**
     * 通过订单id查找订单Orser数据
     * @param orderId
     * @return
     * 如果模型里有模型，这个时候需要自己封住那个数据
     */
    public Order findOrderByOrderId(String orderId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //查询order表，把数据封装到order对象
        String sql1 = "select * from orders where id = ?";
        Order order = queryRunner.query(sql1, new BeanHandler<>(Order.class), orderId);
        //查询orderItem表，把数据封装到order的items属性中
        String sql2 = "select o.*, p.name,p.price from orderitem o,products p where o.product_id = p.id and order_id = ?";
        List<OrderItem> mItems = queryRunner.query(sql2, new ResultSetHandler<List<OrderItem>>() {
            @Override
            public List<OrderItem> handle(ResultSet resultSet) throws SQLException {
                //创建集合对象
                List<OrderItem> items = new ArrayList<OrderItem>();
                while (resultSet.next()) {
                    //创建OrderItem对象
                    OrderItem item = new OrderItem();
                    item.setBuyNum(resultSet.getInt("buynum"));
                    //创建Product对象
                    Product product = new Product();
                    product.setId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    //将product对象装入item里
                    item.setProduct(product);
                    items.add(item);
                }
                return items;
            }
        }, orderId);
        order.setItems(mItems);
        return order;
    }
}
