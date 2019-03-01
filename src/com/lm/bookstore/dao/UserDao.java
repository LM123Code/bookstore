package com.lm.bookstore.dao;

import com.lm.bookstore.model.User;
import com.lm.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户持久层java类。  注：未写接口
 * @author LM_Code
 * @create 2019-03-01-20:13
 */
public class UserDao {
    /*
    添加一个用户
     */
    public void addUser(User user) throws SQLException {
        //1.获取QueryRunner
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //2.sql语句的编写
        String sql = "insert into user" +
                " (username,PASSWORD,gender,email,telephone," +
                " introduce,activeCode,state,role,registTime)" +
                " values(?,?,?,?,?,?,?,?,?,?)";
        //3.编写参数
        List<Object> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getEmail());
        list.add(user.getTelephone());
        list.add(user.getIntroduce());
        list.add(user.getActiveCode());
        list.add(user.getState());
        list.add(user.getRole());
        list.add(user.getRegistTime());
        //4.执行SQL语句
        queryRunner.update(sql,list.toArray());
    }
}
