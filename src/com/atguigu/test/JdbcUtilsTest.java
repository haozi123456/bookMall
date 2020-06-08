package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-05-29 21:57
 */
public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        System.out.println(JdbcUtils.getConnection());
    }
}
