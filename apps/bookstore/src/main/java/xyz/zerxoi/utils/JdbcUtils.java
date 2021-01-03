package xyz.zerxoi.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 由 .classpath 文件可知，src 目录是 src/main/java，所以 druid.properties 应该放在该目录下
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection(); // 从数据库连接池中获取连接
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.set(conn);
        }
        return conn;
    }

    // public static void close(Connection conn) {
    // try {
    // if (conn != null) {
    // conn.close();
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public static void commitAndClose() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Tomcat 底层使用的是线程池
            // 如果不删除下次该线程被其他Servlet调用threadLocal.get()返回值非 null
            threadLocal.remove();
        }

    }

    public static void rollbackAndClose() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Tomcat 底层使用的是线程池
            // 如果不删除下次该线程被其他Servlet调用threadLocal.get()返回值非 null
            threadLocal.remove();
        }
    }
}
