package xyz.zerxoi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xyz.zerxoi.utils.JdbcUtils;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

    public int update(String sql, Object... params) {
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public <T> T queryForOne(Class<T> type, String sql, Object... params) {
        try {
            return queryRunner.query(sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... params) {
        try {
            return queryRunner.query(sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T queryForSingleValue(Class<T> type, String sql, Object... params) {
        try {
            return queryRunner.query(sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
