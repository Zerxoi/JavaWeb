package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.Page;
import xyz.zerxoi.service.BookService;
import xyz.zerxoi.service.impl.BookServiceImpl;

public class ClientBookServlet extends BaseServlet {

    private static final long serialVersionUID = 5531434018626079894L;
    private BookService bookService = new BookServiceImpl();

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
        String url = "client/bookServlet?action=page";
        req.setAttribute("page", bookService.page(page, size, url));
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageBetweenPrice(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String minParam = req.getParameter("min");
        String maxParam = req.getParameter("max");
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");
        int page = 1;
        int size = Page.DEFAULT_PAGE_SIZE;
        int min = 0;
        int max = Integer.MAX_VALUE;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        if (sizeParam != null) {
            size = Integer.parseInt(sizeParam);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("client/bookServlet?action=pageBetweenPrice");
        if (minParam != null && !minParam.equals("")) {
            min = Integer.parseInt(minParam);
            sb.append("&min=" + min);
        }
        if (maxParam != null && !maxParam.equals("")) {
            max = Integer.parseInt(maxParam);
            sb.append("&max=" + max);
        }
        String url = sb.toString();
        req.setAttribute("page", bookService.pageBetweenPrice(page, size, min, max, url));
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
