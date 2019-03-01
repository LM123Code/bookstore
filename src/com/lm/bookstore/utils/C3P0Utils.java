package com.lm.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author LM_Code
 * @create 2019-03-01-20:07
 */
public class C3P0Utils {

    private static DataSource ds  = new ComboPooledDataSource();
    /*
    返回数据源【连接池】
     */
    public static DataSource getDataSource(){
        return ds;
    }
    /*
    返回一个连接
     */
    public static Connection getConnection(){
        try {
            return ds.getConnection();//从连接池获取
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("服务器错误");
        }
    }

    public static void closeAll(Connection conn, Statement statement, ResultSet resultSet){

        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            resultSet = null;
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            statement = null;
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                conn = null;
            }
        }
    }
}
