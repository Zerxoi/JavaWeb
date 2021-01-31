package xyz.zerxoi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter extends HttpFilter {

    private static final long serialVersionUID = -804262998675257315L;

    @Override
    public void init() throws ServletException {
        System.out.println("MyFilter Initializing");
    }
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter doFilter");
        chain.doFilter(req, res);
    }
    @Override
    public void destroy() {
        System.out.println("MyFilter Destroying");
    }
    
}
