package com.lm.bookstore.web.servlet;

import com.lm.bookstore.model.Product;
import com.lm.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LM_Code
 * @create 2019-03-03-17:37
 */
@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductService ps = new ProductService();
        Product p = ps.findBook(id);
        Map<Product, Integer> cart = (Map<Product, Integer>)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new HashMap<Product, Integer>();
            cart.put(p, 1);
        }else{
            //判断map里面是否有当前想购买的商品,equals方法已被重写
            if(cart.containsKey(p)){
                cart.put(p, cart.get(p) + 1);
            }else{
                cart.put(p, 1);
            }
        }
        //打印购物车内信息
        //System.out.println(cart);
        //存session
        request.getSession().setAttribute("cart", cart);

        //响应客户端
        String a1 = "<a href = \"" + request.getContextPath() + "/showProductByPageServlet" + "\">继续购物</a>";
        String a2 = "&nbsp;&nbsp<a href = \"" + request.getContextPath() + "/cart.jsp" + "\">查看购物车</a>";
        response.getWriter().write(a1);
        response.getWriter().write(a2);
    }
}
