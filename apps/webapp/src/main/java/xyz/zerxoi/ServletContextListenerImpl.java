package xyz.zerxoi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 销毁");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext 初始化");
    }
    
}
