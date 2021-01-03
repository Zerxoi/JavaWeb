package xyz.zerxoi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.util.CookieUtils;

public class CookieServlet extends BaseServlet {

    private static final long serialVersionUID = -6745796180998862512L;

    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie yummy_cookie = new Cookie("yummy_cookie", "choco");
        Cookie tasty_cookie = new Cookie("tasty_cookie", "strawberry");
        resp.addCookie(yummy_cookie);
        resp.addCookie(tasty_cookie);
        resp.getWriter().println("Cookie 创建成功");
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            out.println(cookie.getName() + ": " + cookie.getValue());
        }
        System.out.println("yummy_cookie: " + CookieUtils.findCookie("yummy_cookie", cookies).getValue());
    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie yummy_cookie = new Cookie("yummy_cookie", "咖啡");
        resp.addCookie(yummy_cookie);

        Cookie tasty_cookie = CookieUtils.findCookie("tasty_cookie", req.getCookies());
        if (tasty_cookie != null) {
            tasty_cookie.setValue("raspberry");
        }
        resp.addCookie(tasty_cookie);

        PrintWriter out = resp.getWriter();
        out.println("Cookie 修改成功");
    }

    protected void DefaultLifetime(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie bad_cookie = new Cookie("bad_cookie", "banana");
        bad_cookie.setMaxAge(-1);
        resp.addCookie(bad_cookie);

        PrintWriter out = resp.getWriter();
        out.println("bad_cookie 会话关闭时删除");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.findCookie("bad_cookie", req.getCookies());
        if (cookie == null) {
            return;
        }
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        PrintWriter out = resp.getWriter();
        out.println("立即删除 bad_cookie");
    }

    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("perishable_cookie", "banana");
        cookie.setMaxAge(3600);
        resp.addCookie(cookie);

        PrintWriter out = resp.getWriter();
        out.println("perishable_cookie 1小时后过期");
    }

    protected void setScope(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("cookie1", "default");
        resp.addCookie(cookie1);

        Cookie cookie2 = new Cookie("cookie2", "path");
        cookie2.setPath("/path");
        resp.addCookie(cookie2);

        Cookie cookie3 = new Cookie("cookie3", "domain");
        cookie3.setDomain("zerxoi.xyz");
        resp.addCookie(cookie3);
        
        Cookie cookie4 = new Cookie("cookie4", "domain&path");
        cookie4.setDomain("zerxoi.xyz");
        cookie4.setPath("/path");
        resp.addCookie(cookie4);

        PrintWriter out = resp.getWriter();
        out.println("新建作用域 Cookie 成功");
    }
}
