package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-05-31 11:37
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求地址
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userService.login()登录处理业务
        User login = userService.login(new User(null, username, password, null));

        if(login==null){
            //把错误信息和回显到表单项信息保存到Request域中
            req.setAttribute("msg", "用户名/密码错误");
            req.setAttribute("username", username);

            //登陆失败，跳回
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }


    }
}
