package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.Product;
import com.lm.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 更改购物车数量
 * @author LM_Code
 * @create 2019-03-04-13:56
 */
@WebServlet("/changeNumServlet")
public class ChangeNumServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        //2更改购物车数据
        //2.1通过id查找商品
        ProductService ps = new ProductService();
        Product product = ps.findBook(id);
        //2.2通过商品更新session数据
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        //替换
        if(cart.containsKey(product)){//判断是否有商品
            if("0".equals(num)){//移除商品
                cart.remove(product);
            }else{
                cart.put(product, Integer.parseInt(num));
            }
        }
        //重新保存到session
        request.getSession().setAttribute("cart", cart);
        //回到购物车页面
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
