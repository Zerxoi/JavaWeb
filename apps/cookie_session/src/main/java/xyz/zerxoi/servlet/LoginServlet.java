package xyz.zerxoi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -5749766871255367100L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("zerxoi") && password.equals("123456")) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie);
            resp.getWriter().write("登陆成功");
            return;
        }
        resp.getWriter().write("登陆失败");
    }
}
