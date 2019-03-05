package com.lm.bookstore.dao;

import com.lm.bookstore.model.OrderItem;
import com.lm.bookstore.utils.C3P0Utils;
import com.lm.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-04-16:51
 */
public class OrderItemDao {
    /*public void addOrderItems(List<OrderItem> items) throws SQLException {
        QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
        //编写SQL语句
        String sql = "insert into orderitem values(?,?,?)";
        for(OrderItem item : items){
            qu.update(sql, item.getOrder().getId(), item.getProduct().getId(), item.getBuyNum());
        }
    }*/
    //上述函数的批处理实现，只执行一次SQL语句
    public void addOrderItems(List<OrderItem> items) throws SQLException {
        //编写SQL语句
        String sql = "insert into orderitem values(?,?,?)";
        //创建二维数组
        Object[][] params = new Object[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            params[i] = new Object[]{sql, item.getOrder().getId(), item.getProduct().getId(), item.getBuyNum()};
        }
        /*QueryRunner qu = new QueryRunner(C3P0Utils.getDataSource());
        qu.batch(sql, params);*/
        //事务优化
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.batch(ManagerThreadLocal.getConnection(), sql, params);
    }

}
