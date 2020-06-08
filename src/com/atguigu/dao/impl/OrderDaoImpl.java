package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "SELECT `order_id` AS `orderId`,`create_time`AS `createTime`" +
                ",`price` AS `price`,`status` AS `status`,`user_id`  AS `userId` FROM t_order ";
        return  queryForList(Order.class, sql);
    }

    /**
     * 修改订单编号状态
     * @param orderId
     * @param status 0未发货，1已发货，2已签收
     * @return
     */
    @Override
    public int changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set `status` = ? where order_id = ?";
        return update(sql, status,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer user_id) {
        String sql = "SELECT `order_id` AS `orderId`,`create_time`AS `createTime`" +
                ",`price` AS `price`,`status` AS `status`,`user_id`  AS `userId` FROM t_order WHERE `user_id` = ? ";
        return  queryForList(Order.class, sql,user_id);
    }

}
