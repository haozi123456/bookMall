package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNum, int pageSize) {
        Page<Book> page = new Page<>();
        //设置每页大小
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //设置总页数
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount % pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);


        page.setPageNum(pageNum);

        int begin=( page.getPageNum()-1 ) * pageSize;
        List<Book> items = bookDao.queForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNum, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        //设置每页大小
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //设置总页数
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount % pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNum(pageNum);
        //查询当前页数据
        int begin=( page.getPageNum()-1 ) * pageSize;
        //设置当前页数据
        List<Book> items = bookDao.queForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
