package xyz.zerxoi.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xyz.zerxoi.utils.JdbcUtils;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T queryForOne(Class<T> type, String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T queryForSingleValue(Class<T> type, String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
