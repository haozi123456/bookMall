package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-05 19:04
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     *实现修改商品数量
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = WebUtils.parseInt(req.getParameter("count"),1) ;
        int id = WebUtils.parseInt(req.getParameter("id"),0) ;

        //修改数量不得小于1
        if(count<1){
            resp.sendRedirect(req.getHeader("Referer"));
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart!=null){
            cart.updateCount(id, count);
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }


        /**
         *实现清空购物车
         */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }


        /**
         *实现删除购物车的一项商品
         */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

        /**
         *实现加入图书到购物车
         */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);

        req.getSession().setAttribute("lastBookName", cartItem.getName());

        //获取原地址，请求重定向
        resp.sendRedirect( req.getHeader("Referer") );

    }


}
