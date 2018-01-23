package com.koubei.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class com_DruidJdbcHelper {
    private com_DruidJdbcHelper() {
    }

    private static DruidDataSource ds = null;

    private static class LazyHolder {
        private static final com_DruidJdbcHelper JDBC = new com_DruidJdbcHelper();
    }

    /**
     * 获取JDBC实例
     *
     * @return
     */
    public static synchronized final com_DruidJdbcHelper getInstance() {
        return LazyHolder.JDBC;
    }

    /**
     * 获取sql链接
     *
     * @return
     * @throws SQLException
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     */
    private static Connection getConnection() throws SQLException, ConfigurationException, ClassNotFoundException {
        String[] keys = new String[]{"driverClassName", "jdbcUrl", "username", "password", "initialSize", "filters", "minIdle"};
        Map<String, String> configMap = com_ConfigHelper.getConfigStrings("druid.properties", keys);
        String driverName = configMap.get("driverClassName");
        Class.forName(driverName);
        String url = configMap.get("jdbcUrl");
        String username = configMap.get("username");
        String password = configMap.get("password");
        String filters = configMap.get("filters");
        Integer initialSize = com_StringHelper.toInteger(configMap.get("initialSize"), 1);
        Integer minIdle = com_StringHelper.toInteger(configMap.get("minIdle"), 1);
        ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverName);
        ds.setInitialSize(initialSize);
        ds.setMinIdle(minIdle);
        Connection connection = ds.getConnection();
        return connection;
    }

    /**
     * Close a <code>ResultSet</code>, avoid closing if null.
     *
     * @param rs ResultSet to close.
     * @throws SQLException if a database access error occurs
     */
    private static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Close a <code>Statement</code>, avoid closing if null.
     *
     * @param stmt Statement to close.
     * @throws SQLException if a database access error occurs
     */
    private static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    private static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs)
            throws SQLException {
        close(conn);
        close(stmt);
        close(rs);
    }

}
