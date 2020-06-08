package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Neo 1076437118@qq.com
 * @function
 * @create 2020-06-04 21:37
 */
public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求参数pageNum和pageSize
        int pageNum = WebUtils.parseInt(req.getParameter("pageNum"),1 );
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService中的page(pageNum,pageSize);返回page对象
        Page<Book> page = bookService.page(pageNum,pageSize);

        page.setUrl("client/bookServlet?action=page");

        //3.保存page对象到request域中，供jsp使用
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求参数pageNum和pageSize,最大、最小价格
        int pageNum = WebUtils.parseInt(req.getParameter("pageNum"),1 );
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2.调用BookService中的page(pageNum,pageSize);返回page对象
        Page<Book> page = bookService.pageByPrice(pageNum,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3.保存page对象到request域中，供jsp使用
        req.setAttribute("page", page);
        //4.请求转发到pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
}
