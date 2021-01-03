package xyz.zerxoi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirection extends HttpServlet {
    private static final long serialVersionUID = -2057551505456598143L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.setStatus(302);
        // resp.setHeader("Location", "http://localhost:8080/webapp/servletB");
        resp.sendRedirect("http://localhost:8080/webapp/servletB");
    }
}
