package com.atguigu.test;

import com.atguigu.web.UserServlet;

import java.lang.reflect.Method;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-01 15:10
 */
public class UserServletTest {
    public void login(){
        System.out.println("login方法");
    }

    public void regist(){
        System.out.println("regist方法");
    }

    public void updateUser(){
        System.out.println("updateUser方法");
    }

    public void updateUserPassword(){
        System.out.println("updateUserPassword方法");
    }

    public static void main(String[] args) {
        String action = "login";

        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);

            //调用目标业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
