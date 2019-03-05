package com.lm.bookstore.dao;

import com.lm.bookstore.model.Product;
import com.lm.bookstore.utils.C3P0Utils;
import com.lm.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-03-10:39
 */
public class ProductDao {
    /**
     * 计算商品某一类别的总记录数
     */
    public long count(String category) throws SQLException {
        long count = 0;//定义记录数变量0
        QueryRunner queryRunner =new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from products where 1 = 1";//查询所有商品
        if(category != null && !"".equals(category)){
            sql += " and category = ?";
            count = (long)queryRunner.query(sql, new ScalarHandler(), category);
        }else{
            count = (long)queryRunner.query(sql, new ScalarHandler());
        }
        return count;
    }

    /**
     *  查取某一商品固定页的内容
     * @param category 类型
     * @param page 当前页
     * @param pagesize 每页显示条数
     * @return
     */
    public List<Product> findBooks(String category, int page, int pagesize) throws SQLException {
        int start = (page - 1)*pagesize;//当前页开始位置
        int length = pagesize;//当前页大小
        List<Object> prams = new ArrayList<Object>();//存放参数
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from products where 1=1";
        if(category != null && !"".equals(category)){
            sql += " and category = ?";
            prams.add(category);//加入参数
        }
        sql += " limit ?,?";
        prams.add(start);//加入参数
        prams.add(length);//加入参数
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), prams.toArray());
    }

    /**
     * 根据id查找上商品
     * @param id
     * @return
     */
    public Product findBook(String id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        return queryRunner.query("select * from products where id = ?", new BeanHandler<Product>(Product.class), id);
    }

    /**
     * 更新库存
     */
    public void updateNum(int product_id, int num) throws SQLException {
        String sql = "update products set pnum = pnum - ? where id = ?";
        /*QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        queryRunner.update(sql, num, product_id);*/
        //事务优化
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(ManagerThreadLocal.getConnection(), sql, num, product_id);
    }
}
