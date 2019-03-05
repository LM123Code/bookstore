package com.lm.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author LM_Code
 * @create 2019-03-04-20:07
 */
public class ManagerThreadLocal {
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    public static Connection getConnection(){
        try{
            Connection conn = tl.get();
            //第一次是null
            if(conn == null){
                conn = C3P0Utils.getConnection();
                tl.set(conn);
                System.out.println("第一次从数据源获取connection对象：" + conn);
            }else{
//                System.out.println("第n次从ThreadLocal获取connection对象");
            }
            return conn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 开启事务
     */
    public static void beginTransaction(){
        try{
            getConnection().setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 回滚事物
     */
    public static void rollbackTransaction(){
        try{
            getConnection().rollback();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public static void commitTransaction(){
        try {
            getConnection().commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 关闭事务
     */
    public static void close(){
        try{
            getConnection().close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
