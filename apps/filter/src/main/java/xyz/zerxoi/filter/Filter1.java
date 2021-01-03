package xyz.zerxoi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Filter1 Thread: " + Thread.currentThread().getName());
        System.out.println("Filter1 Request: " + request);
        System.out.println("Filter1 before");
        chain.doFilter(request, response);
        System.out.println("Filter1 after");

    }
}
