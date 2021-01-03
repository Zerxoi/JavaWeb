package xyz.zerxoi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AdminFilter implements Filter {

    public AdminFilter() {
        System.out.println("1. Constructor");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2. init");
        System.out.println(filterConfig.getFilterName());
        System.out.println(filterConfig.getInitParameter("username"));
        System.out.println(filterConfig.getInitParameter("password"));
        System.out.println(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("3. doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            // 请求拦截
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 请求放行
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        System.out.println("4. destroy");
    }

}