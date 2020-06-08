package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 10:11
 */
public interface OrderDao {

    //保存订单到数据库
    public int saveOrder(Order order);

    //_______________以下管理员可用_______________

    //查询所有订单
    public List<Order> queryOrders();

    //修改订单状态
    public int changeOrderStatus(String orderId,int status);

    //根据用户编号查询订单信息
    public List<Order>  queryOrderByUserId(Integer userId);
}
