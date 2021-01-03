package xyz.zerxoi.utils;

import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {

    private static DruidDataSource dataSource;

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
    public static DruidDataSource getDataSource() {
        return dataSource;
    }

}
