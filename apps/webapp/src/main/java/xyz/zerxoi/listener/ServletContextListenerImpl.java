package xyz.zerxoi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener Destroy " + sce.getServletContext());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener Initialize " + sce.getServletContext());
    }
    
}
