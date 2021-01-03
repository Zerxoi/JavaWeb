package xyz.zerxoi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.pojo.User;

public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        req.getSession().setAttribute("user", new User(username, password));
        resp.getWriter().write("登陆成功");
    }
}
