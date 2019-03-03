package com.lm.bookstore.service;

import com.lm.bookstore.dao.ProductDao;
import com.lm.bookstore.model.PageResult;
import com.lm.bookstore.model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-03-11:19
 */
public class ProductService {
    ProductDao productDao = new ProductDao();
    public PageResult<Product> findBook(String category, int page){
        try {
            PageResult<Product> pr = new PageResult<>();//创建模型，存储查询结果
            long totalCount = productDao.count(category);//按类型查询总条数
            pr.setTotalCount(totalCount);//在模型中设置总条数
            int totalPage = (int) Math.ceil(totalCount*1.0/pr.getPageSize());//计算总页数
            pr.setTotalPage(totalPage);//在模型中设置总页数
            pr.setCurrentPage(page);//设置当前页码
            //查询某一类别、某一页的内容
            List<Product> list = productDao.findBooks(category, page, pr.getPageSize());
            pr.setList(list);
            return pr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id查找商品
     * @param id
     * @return
     */
    public Product findBook(String id){
        try {
            return productDao.findBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
