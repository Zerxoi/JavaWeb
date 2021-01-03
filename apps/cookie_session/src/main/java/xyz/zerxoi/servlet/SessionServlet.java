package xyz.zerxoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends BaseServlet {

    private static final long serialVersionUID = -6916449314278410356L;

    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = session.getId();
        boolean isNew = session.isNew();

        PrintWriter out = resp.getWriter();
        out.println("Session's id is " + id);
        out.println("Session is new ? " + isNew);
    }

    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key", "value");
        resp.getWriter().println("Session 设置属性成功");
    }

    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attr = req.getSession().getAttribute("key");
        resp.getWriter().println("Session 的key键的值为 " + attr);
    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
        resp.getWriter().println("Session 的默认超时时长为 " + maxInactiveInterval + " s");
    }

    protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setMaxInactiveInterval(3);

        resp.getWriter().println("当前Session 3 秒后超时");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        resp.getWriter().println("当前Session 无效化");
    }
}
