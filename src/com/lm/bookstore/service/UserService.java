package com.lm.bookstore.service;

import com.lm.bookstore.dao.UserDao;
import com.lm.bookstore.exception.UserException;
import com.lm.bookstore.model.User;
import com.lm.bookstore.utils.SendJMail;
import java.sql.SQLException;

/**
 * 业务层实现类，  注：未写接口
 * @author LM_Code
 * @create 2019-03-01-20:29
 */
public class UserService {
    //创建dao
    UserDao userDao = new UserDao();
    public void register(User user) throws UserException {
        try {
            //往数据库添加用户
            userDao.addUser(user);
            String link = "http://localhost:8080/bookstore/active?activeCode=" + user.getActiveCode();
            String html = "<a href = \""+ link +"\">欢迎您注册网上书城账号，请点击激活</a>";
            System.out.println(html);
            //发送邮件
            SendJMail.sendMail(user.getEmail(),html);//传入发送地址和发送内容
        } catch (SQLException e) {
            throw new UserException("用户注册失败，用户名重复");
        }
    }
    /*
    激活用户
     */
    public void activeUser(String activeCode) throws UserException {
        try {
            //1.查询激活码的用户是否存在
            User user = userDao.findUserByActiveCode(activeCode);
            if(user == null){
                throw new UserException("用户不存在");
            }
            if(user != null && user.getState() == 1){
                throw new UserException("用户已激活");
            }
            userDao.updateState(activeCode);
        } catch (SQLException e) {
            throw new UserException("激活失败");
        }
    }
}
