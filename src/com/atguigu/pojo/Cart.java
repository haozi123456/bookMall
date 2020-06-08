package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 购物车对象
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-05 16:48
 */
public class Cart {
    /**
     * key是商品编号，value是商品对象
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());

        if(item==null){
            items.put(cartItem.getId(), cartItem);
        }else{
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * 清空购物车
     * @param
     */
    public void clear(){
        items.clear();
    }
    /**
     * 修改商品数量
     * @param id,count
     */
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount="+getTotalCount()+
                ", totalPrice="+getTotalPrice()+
                ", items=" + items +
                '}';
    }
}
