package xyz.zerxoi;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletA extends HttpServlet {
    private static final long serialVersionUID = -2345771005431135205L;

    protected void servletInit(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletInit(req, resp);
        RequestDispatcher rd = req.getRequestDispatcher("servletB");
        PrintWriter out = resp.getWriter();
        System.out.println("Servlet 中的请求：" + req);
        System.out.println("请求调度前的请求属性foo的值：" + req.getAttribute("foo"));
        // 在 forward 之前提交会抛出 IllegalStateException
        // out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // out.flush();
        rd.forward(req, resp);
        // forward 之后的关于 HttpServletResponse 全都无效
        // out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // out.flush();
        // resp.setStatus(404);
        System.out.println("请求调度后的请求属性foo的值：" + req.getAttribute("foo"));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletInit(req, resp);
        RequestDispatcher rd = req.getRequestDispatcher("servletB");
        PrintWriter out = resp.getWriter();
        System.out.println("Servlet 中的请求：" + req);
        System.out.println("请求调度前的请求属性foo的值：" + req.getAttribute("foo"));
        out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        out.flush();
        rd.include(req, resp);
        out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        out.flush();
        System.out.println("请求调度前的请求属性foo的值：" + req.getAttribute("foo"));
        out.close();
    }
}