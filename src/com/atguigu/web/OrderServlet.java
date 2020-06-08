package com.atguigu.web;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-06 11:25
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    //创建订单方法（）
    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        User loginUser = (User) req.getSession().getAttribute("user");
        //用户未登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp");
        } else {
            Integer userId = loginUser.getId();

            String orderId = orderService.creatOrder(cart, userId);

            //返回含空格字符串说明库存不够，返回的是库存+" "+数目
            if (orderId.contains(" ")) {
                String[] strings = orderId.split("\\s+");
                Integer stock = Integer.parseInt(strings[0]);
                String bookName = strings[1];
                req.getSession().setAttribute("bookName", bookName);
                req.getSession().setAttribute("stock", stock);
                //返回原购物车页面
                resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
            } else {
                req.getSession().setAttribute("orderId", orderId);
                //这里防止表单重复提交仍然要使用重定向
                resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
            }
        }

    }

    //  管理员/用户查询所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        //判断是否为管理员admin
        if ("admin".equals(user.getUsername())) {
            List<Order> orders = orderService.showAllOrders();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
        } else {
            List<Order> userOrders = orderService.showOrdersByUserId(user.getId());
            req.setAttribute("user_orders", userOrders);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
        }
    }

    //管理员根据订单编号发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");
    }

    //查看订单详情
    protected void showOrderByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User user = (User) req.getSession().getAttribute("user");

        if ("admin".equals(user.getUsername())) {
            List<OrderItem> orderAdminDetails = orderService.showOrderByOrderId(orderId);
            req.setAttribute("orderAdminDetails", orderAdminDetails);
            req.getRequestDispatcher("/pages/manager/orderItem_manager.jsp").forward(req, resp);
        } else {
            //否则就是用户查看详情页面
            List<OrderItem> orderUserDetails = orderService.showOrderByOrderId(orderId);
            req.setAttribute("orderUserDetails", orderUserDetails);
            req.getRequestDispatcher("/pages/order/orderItem_user.jsp").forward(req, resp);
        }
    }

    //签收订单
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");

        //调用Service将指定的订单ID修改状态为已签收。
        orderService.receiveOrderByOrderId(orderId);

        //请求重定向到原来的页面。
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");

    }
}
