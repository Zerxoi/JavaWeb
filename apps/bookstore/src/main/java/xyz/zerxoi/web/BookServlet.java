package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.Book;
import xyz.zerxoi.pojo.Page;
import xyz.zerxoi.service.BookService;
import xyz.zerxoi.service.impl.BookServiceImpl;
import xyz.zerxoi.utils.WebUtils;

public class BookServlet extends BaseServlet {

    private static final long serialVersionUID = -4856172946365557878L;
    private BookService bookService = new BookServiceImpl();

    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.insertBook(book);
        // 当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户刷新后就会发起浏览器记录的最后一次请求。
        // 请求转发是一次请求，每次刷新都会向数据库中添加一个新的对象并跳转至图书查询页面。
        // req.getRequestDispatcher("/manager/bookServlet?action=page").forward(req,
        // resp);
        // 重定向是两次请求，第一次请求返回第二次请求的地址，第二次请求访问第一次响应的地址
        // 所以刷新不会向数据库中添加新的数据
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&page=" + req.getParameter("page"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&page=" + req.getParameter("page"));
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("book", bookService.queryBookById(id));
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?page=" + req.getParameter("page")).forward(req, resp);
        ;
    }

    // protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     List<Book> books = bookService.queryBooks();
    //     req.setAttribute("books", books);
    //     req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    // }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&page=" + req.getParameter("page"));
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");
        int page = 1;
        int size = Page.DEFAULT_PAGE_SIZE;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        if (sizeParam != null) {
            size = Integer.parseInt(sizeParam);
        }
        String url = "manager/bookServlet?action=page";
        req.setAttribute("page", bookService.page(page, size, url));
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
