package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-05-31 9:01
 */
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUSer() {
        userService.registUser(new User(null, "bbj668","666666", "bbj668@qq.com"));
        userService.registUser(new User(null, "wq1996","123456", "wq1996@qq.com"));

    }

    @Test
    public void login() {
        User login = userService.login(new User(null, "wzg168", "123456", null));
        System.out.println(login);
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("wzg168")){
            System.out.println("存在");
        }else {
            System.out.println("可用");
        }
    }
}