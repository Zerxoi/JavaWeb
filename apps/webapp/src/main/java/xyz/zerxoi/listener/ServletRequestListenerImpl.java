package xyz.zerxoi.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ServletRequestListenerImpl implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener Destroy " + sre.getServletRequest());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener Initialize " + sre.getServletRequest());
    }
}
