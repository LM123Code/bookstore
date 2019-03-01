package com.lm.bookstore.service;

import com.lm.bookstore.dao.UserDao;
import com.lm.bookstore.model.User;

import java.sql.SQLException;

/**
 * 业务层实现类，  注：未写接口
 * @author LM_Code
 * @create 2019-03-01-20:29
 */
public class UserService {
    //创建dao
    UserDao userDao = new UserDao();
    public void register(User user){
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
