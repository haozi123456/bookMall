package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 10:14
 */
public interface OrderItemDao {
    //保存订单项到相应表中
    public int saveOrderItem(OrderItem orderItem);

    //根据订单号查询订单项明细
    public List<OrderItem> queryOrderItemByOrderId(String OrderId);

}
