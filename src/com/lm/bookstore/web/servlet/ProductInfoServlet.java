package com.lm.bookstore.web.servlet;

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
 * @create 2019-03-03-16:08
 */
@WebServlet("/productInfoServlet")
public class ProductInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductService ps = new ProductService();
        Product product = ps.findBook(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/product_info.jsp").forward(request, response);
    }
}
