package xyz.zerxoi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletB extends HttpServlet {
    private static final long serialVersionUID = -3733534082102273529L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handler(req, resp);
    }

    protected void handler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestDispatcher 中的请求：" + req);
        req.setAttribute("foo", "bar");
        req.setCharacterEncoding("UTF-8");
        // include 中的 resp 操作都被忽略
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(req.getMethod());
        out.println();
        out.println("getMethod:" + req.getMethod());
        out.println("getRequestURI:" + req.getRequestURI());
        out.println("getRequestURL:" + req.getRequestURL());
        out.println("getRemoteHost:" + req.getRemoteHost());
        out.println("getParameterMap:");
        out.println(map2String(req.getParameterMap()));
        out.println("getHeaderNames:");
        out.println(enumeration2String(req));
        out.println("UTF-8测试");
        out.close();
    }

    protected String map2String(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String[]> entry = iterator.next();
            sb.append(entry.getKey() + ": " + Arrays.toString(entry.getValue()) + "\n");
        }
        return sb.toString();
    }

    protected String enumeration2String(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enumeration = req.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String element = enumeration.nextElement();
            sb.append(element + ": " + req.getHeader(element) + "\n");
        }
        return sb.toString();
    }
}
