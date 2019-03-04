package com.lm.bookstore.dao;

import com.lm.bookstore.model.Order;
import com.lm.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-04-16:48
 */
public class OrderDao {
    public void addOrder(Order order) throws SQLException {
        QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
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
        qu.update(sql, params.toArray());
    }
}
