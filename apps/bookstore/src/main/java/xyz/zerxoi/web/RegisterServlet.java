package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.User;
import xyz.zerxoi.service.UserService;
import xyz.zerxoi.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 3588286203013581325L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if (!"bnbnp".equalsIgnoreCase(code)) {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }
        UserService userService = new UserServiceImpl();
        if (userService.existsUsername(username)) {
            req.setAttribute("msg", "用户名 " + username + " 已存在");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }
        userService.register(new User(null, username, password, email));
        req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
    }
}
