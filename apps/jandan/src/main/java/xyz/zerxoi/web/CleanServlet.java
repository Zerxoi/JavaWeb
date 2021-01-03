package xyz.zerxoi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.zerxoi.dao.impl.CommentDaoImpl;

public class CleanServlet extends HttpServlet {

    private static final long serialVersionUID = -6074735428932656980L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new CommentDaoImpl().deleteOneDayAgo();
    }
    
}
