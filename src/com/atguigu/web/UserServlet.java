package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.test.UserServletTest;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-01 12:02
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void logout(HttpServletRequest req,HttpServletResponse resp) {
        //用户注销
        //销毁Session或Session中的用户信息
        req.getSession().invalidate();
        //重定向
        try {
            resp.sendRedirect(req.getContextPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest req,HttpServletResponse resp) {
        //1.获取请求地址
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userService.login()登录处理业务
        User login = userService.login(new User(null, username, password, null));

        if (login == null) {
            //把错误信息和回显到表单项信息保存到Request域中
            req.setAttribute("msg", "用户名/密码错误");
            req.setAttribute("username", username);

            //登陆失败，跳回
            try {
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("登录成功");
            try {
                //保存用户登录之后的信息到Session域中
                req.getSession().setAttribute("user",login);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void regist(HttpServletRequest req,HttpServletResponse resp) {

        //验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());



//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token!=null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existUsername(username)) {
//                System.out.println("用户名[" + username + "]已存在!");
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
//        跳回注册页面
                try {
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //      可用
//                调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面
                try {
                    req.getSession().setAttribute("user",user);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            try {
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
