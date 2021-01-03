package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.User;
import xyz.zerxoi.service.UserService;
import xyz.zerxoi.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -4702970692036711659L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();
        if (userService.login(new User(null, username, password, null)) == null) {
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
    }
}
