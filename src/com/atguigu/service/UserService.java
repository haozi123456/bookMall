package com.atguigu.service;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImp;
import com.atguigu.pojo.User;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-05-31 8:46
 */
public interface UserService {

    public void registUser(User user);

    /**
     *
     * @param user
     * @return  返回null说明登陆失败
     */

    public User login(User user);

    /**
     *
     * @param username
     * @return  返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existUsername(String username);
}
