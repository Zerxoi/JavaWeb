package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.Cart;
import xyz.zerxoi.pojo.User;
import xyz.zerxoi.service.OrderService;
import xyz.zerxoi.service.impl.OrderServiceImpl;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    private static final long serialVersionUID = 3093710145847725282L;

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            // req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Integer userId = user.getId();
        String orderId = orderService.create(cart, userId);
        cart.clear();
        // 防止订单重复提交
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
