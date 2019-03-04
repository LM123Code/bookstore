package com.lm.bookstore.dao;

import com.lm.bookstore.model.OrderItem;
import com.lm.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-04-16:51
 */
public class OrderItemDao {
    public void addOrderItems(List<OrderItem> items) throws SQLException {
        QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
        //编写SQL语句
        String sql = "insert into orderitem values(?,?,?)";
        for(OrderItem item : items){
            qu.update(sql, item.getOrder().getId(), item.getProduct().getId(), item.getBuyNum());
        }
    }

}
