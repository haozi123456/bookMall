package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 11:13
 */
public class OrderServiceTest {

    @Test
    public void creatOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"不要放弃" , 1, new BigDecimal(1000), new BigDecimal(1000)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println("订单号："+orderService.creatOrder(cart, 1));
    }
}