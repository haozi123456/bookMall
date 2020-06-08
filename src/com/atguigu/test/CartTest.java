package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-05 18:31
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"不要放弃" , 1, new BigDecimal(1000), new BigDecimal(1000)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"不要放弃" , 1, new BigDecimal(1000), new BigDecimal(1000)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"不要放弃" , 1, new BigDecimal(1000), new BigDecimal(1000)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"从入门到精通" , 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"不要放弃" , 1, new BigDecimal(1000), new BigDecimal(1000)));

        cart.updateCount(1,1);
        System.out.println(cart);
    }
    @Test
    public void stringTest(){
        String string = "12 乱世";
        System.out.println(string.contains(" "));
        for(String s:string.split("\\s+")){
            System.out.println(s);
        }
    }
}