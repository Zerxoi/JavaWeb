package xyz.zerxoi.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    // public static void main(String[] args) {
    //     Connection conn = JdbcUtils.getConnection();
    //     try {
    //         conn.setAutoCommit(false); // 关闭事务自动提交

    //         // ...

    //         conn.commit(); // 手动提交事务
    //     } catch (SQLException e) {
    //         try {
    //             conn.rollback();
    //         } catch (SQLException e1) {
    //             e1.printStackTrace(); // 手动回滚事务
    //         }
    //     } finally {
    //         JdbcUtils.close(conn);
    //     }
    // }
}
