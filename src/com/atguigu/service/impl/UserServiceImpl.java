package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImp;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-05-31 8:52
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImp();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername()
        , user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
