package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 10:51
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String creatOrder(Cart cart, Integer userId) {
        //创建一个 时间戳+用户id 的订单号
        String orderId = System.currentTimeMillis() + "" + userId;

        //1.遍历购物车中的每一个商品项转换为订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取单个购物车内容
            CartItem cartItem = entry.getValue();
            //修改该图书的销量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            //库存不够
            if(book.getStock()<cartItem.getCount()){
                return Integer.toString(book.getStock())+" "+book.getName();
            }else{
                OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);

                orderItemDao.saveOrderItem(orderItem);

                int i=10/0;

                book.setSales(book.getSales() + orderItem.getCount());
                book.setStock(book.getStock() - orderItem.getCount());

                bookDao.updateBook(book);
//                cart.deleteItem(cartItem.getId());
            }
        }
        //保存订单
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderByOrderId(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showOrdersByUserId(Integer userId) {
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public int receiveOrderByOrderId(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }


}
