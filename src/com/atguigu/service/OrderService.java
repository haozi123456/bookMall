package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 10:50
 */

public interface OrderService {
    /**
     * 根据购物车生成订单，并返回订单号
     * @param cart  当前购物车
     * @param userId 用户Id
     * @return
     */
    public String creatOrder(Cart cart,Integer userId);

    /**
     * 返回所有订单信息
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 发货，更新订单状态
     * @param orderId
     */
    public int sendOrder(String orderId);

    /**
     * 根据订单编号查询订单
     * @param orderId
     */
    public List<OrderItem> showOrderByOrderId(String orderId);

    /**
     * 根据用户号显示该用户订单
     * @param userId
     */
    public List<Order> showOrdersByUserId(Integer userId);

    /**
     * 用户签收订单改变订单状态
     * @param orderId
     */
    public int receiveOrderByOrderId(String orderId);
}
