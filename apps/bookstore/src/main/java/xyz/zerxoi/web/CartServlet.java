package xyz.zerxoi.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import xyz.zerxoi.pojo.Book;
import xyz.zerxoi.pojo.Cart;
import xyz.zerxoi.pojo.CartItem;
import xyz.zerxoi.service.BookService;
import xyz.zerxoi.service.impl.BookServiceImpl;

public class CartServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.add(cartItem);
        session.setAttribute("lastName", cartItem.getName());
        // 重定向回原来商品所在页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.add(cartItem);
        session.setAttribute("lastName", cartItem.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", cart.getTotalCount());
        map.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.delete(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
