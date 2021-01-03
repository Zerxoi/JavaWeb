# Listener（监听器）

- Listener监听器是JavaWeb的三大组件之一（Servlet程序，Filter过滤器，Listener监听器）
- Listener是JaveEE规范，也就是一个接口
- 监听器的作用是监听某种事物的变化，然后通过回调函数，反馈给客户（程序）去做相应的处理。

Servlet中一共有4个Listener接口，分别是`ServletContextListener`, `ServletRequestListener`, `ServletContextAttributeListener`,`ServletRequestAttributeListener`。它们都共同继承`EventListener` 接口，`EventListener`是一个空接口。

## 使用步骤

1. 编写一个类去实现上述4个接口的其中之一
2. 实现接口中的方法
3. 在 `web.xml` 中使用`<listener>`元素配置监听器

## ServletContextListener
```java
public interface ServletContextListener extends EventListener {
    // 接收有关Web应用程序初始化过程正在启动的通知时调用该方法
    // 先初始化ServletContext，调用该方法，再初始化过滤器和Servlet
    default public void contextInitialized(ServletContextEvent sce) {}
    // 接收有关ServletContext将要关闭的通知时调用该方法
    // 先销毁过滤器和Servlet之后，再销毁ServletContext，调用该方法
    default public void contextDestroyed(ServletContextEvent sce) {}
}
```

## ServletRequestListener

```java
public interface ServletRequestListener extends EventListener {

    default public void requestDestroyed(ServletRequestEvent sre) {}

    default public void requestInitialized(ServletRequestEvent sre) {}
}
```

## ServletContextAttributeListener

```java
public interface ServletContextAttributeListener extends EventListener {

    default public void attributeAdded(ServletContextAttributeEvent event) {}

    default public void attributeRemoved(ServletContextAttributeEvent event) {}

    default public void attributeReplaced(ServletContextAttributeEvent event) {}
}
```

## ServletRequestAttributeListener

```java
public interface ServletRequestAttributeListener extends EventListener {

    default public void attributeAdded(ServletRequestAttributeEvent srae) {}

    default public void attributeRemoved(ServletRequestAttributeEvent srae) {}

    default public void attributeReplaced(ServletRequestAttributeEvent srae) {}
}
```