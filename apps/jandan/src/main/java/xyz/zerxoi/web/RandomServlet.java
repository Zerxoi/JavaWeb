package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.service.BaseService;

public class RandomServlet extends HttpServlet{

    private static final long serialVersionUID = -1793020942047908338L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseService.nextComment();
        RequestDispatcher rd = req.getRequestDispatcher("/");
        rd.forward(req, resp);
    }
}
