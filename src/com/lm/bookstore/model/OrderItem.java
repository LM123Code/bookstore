package com.lm.bookstore.model;

/**
 * 订单详情
 * @author LM_Code
 * @create 2019-03-04-15:12
 */
public class OrderItem {
    private int buyNum;//购买的数量
    private Order order;//所属订单
    private Product product;//商品

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order.getId() +
                ", product=" + product.getName() +
                ", buyNum=" + buyNum +
                '}';
    }
}
