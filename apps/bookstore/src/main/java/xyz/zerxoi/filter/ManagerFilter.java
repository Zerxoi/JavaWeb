package xyz.zerxoi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ManagerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
    
}
