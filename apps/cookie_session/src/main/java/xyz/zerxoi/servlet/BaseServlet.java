package xyz.zerxoi.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 7666909535409712948L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initServlet(req, resp);
        reflectInvokeMethod(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initServlet(req, resp);
        reflectInvokeMethod(req, resp);
    }

    private void initServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=UTF-8");
    }

    private void reflectInvokeMethod(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Method method = this.getClass().getDeclaredMethod(req.getParameter("action"), HttpServletRequest.class,
                    HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
