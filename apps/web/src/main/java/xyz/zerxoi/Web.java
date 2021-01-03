package xyz.zerxoi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Web extends HttpServlet {

    private static final long serialVersionUID = -1484257762427380604L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(req.getContextPath());
        out.write("<br>");
        out.write(new Greeting("Rose").greeting("Jack"));
    }
}
