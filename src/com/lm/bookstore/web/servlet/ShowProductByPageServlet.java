package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.PageResult;
import com.lm.bookstore.model.Product;
import com.lm.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LM_Code
 * @create 2019-03-03-11:38
 */
@WebServlet("/showProductByPageServlet")
public class ShowProductByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String category = request.getParameter("category");
        String pageStr = request.getParameter("page");
        int page = 1;
        if(pageStr != null && !"".equals(pageStr)){
            page = Integer.parseInt(pageStr);//把字符串转为int
        }

        //2调用service
        ProductService ps = new ProductService();
        PageResult<Product> pageResult = ps.findBook(category, page);

        //存放结果
        request.setAttribute("pageResult", pageResult);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }
}
