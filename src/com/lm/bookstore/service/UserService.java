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
    /*
    添加用户
     */
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
    /*
    根据用户名和密码登录
     */
    public User login(String username, String password) throws UserException{
        //1.查询
        try {
            User user = userDao.findUserByUsernameAndPassword(username, password);
            //2.判断
            if(user == null){
                throw new UserException("用户名或密码不正确");
            }
            if(user.getState() == 0){
                throw new UserException("用户未激活，请先登录邮箱进行激活");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("登录失败");
        }
    }
    /*
    根据id查找user
     */
    public User findUserById(String id) throws UserException{
        try {
            User user = userDao.findUserById(id);
            if(user == null){
                throw new UserException("用不存在");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("未知错误");
        }
    }
    /*
    更改用户信息
     */
    public void modifyUserInfo(User user) throws UserException{
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            throw new UserException("未知错误");
        }
    }
}
