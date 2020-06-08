package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-03 11:10
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "国哥真帅", "191125", new BigDecimal(999), 999, 0, null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queForPageItems() {
        for(Book book : bookDao.queForPageItems(8, 4)){
            System.out.println(book);
        }
    }

    @Test
    public void queForPageItemsByPrice() {
        for(Book book : bookDao.queForPageItemsByPrice(0, 4,10,50)){
            System.out.println(book);
        }
    }
};