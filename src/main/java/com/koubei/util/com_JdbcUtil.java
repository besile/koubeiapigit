package com.koubei.util;

import org.apache.commons.configuration2.ex.ConfigurationException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class com_JdbcUtil {
    private com_JdbcUtil() {
    }

    private static DataSource ds = null;

    private static class LazyHolder {
        private static final com_JdbcUtil JDBC = new com_JdbcUtil();
    }

    /**
     * 获取JDBC实例
     *
     * @return
     */
    public static final com_JdbcUtil getInstance() {
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
        String[] keys = new String[]{"driverClassName", "url", "username", "password"};
        Map<String, String> configMap = com_ConfigHelper.getConfigStrings("jdbc.properties", keys);
        String driverName = configMap.get("driverClassName");
        Class.forName(driverName);
        String url = configMap.get("url");
        String username = configMap.get("username");
        String password = configMap.get("password");
        Connection connection = DriverManager.getConnection(url, username, password);
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


    private static void fillStatement(PreparedStatement stmt, Object... params)
            throws SQLException {

        if (params == null || params.length == 0) {
            return;
        }
        CallableStatement call = null;
        if (stmt instanceof CallableStatement) {
            call = (CallableStatement) stmt;

        }
        for (int i = 1; i <= params.length; i++) {
            if (params[i-1] != null) {
                if (call != null && i == params.length && params[i-1].equals(false)) {
                    call.registerOutParameter(i, Types.INTEGER);
                } else {
                    stmt.setObject(i, params[i - 1]);
                }
            }
        }

    }

    public static CachedRowSet query(String sql) throws SQLException, ConfigurationException, ClassNotFoundException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);
            return crs;
        } finally {
            close(conn, stmt, rs);
        }
    }

    public static CachedRowSet query(String sql, Object... params)
            throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            fillStatement(stmt, params);
            rs = stmt.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);
            return crs;
        } finally {
            close(conn, stmt, rs);
        }
    }

    /**
     * @param @param  sql
     * @param @param  params
     * @param @return
     * @param @throws SQLException 设定文件
     * @return List<CachedRowSet> 返回类型
     * @throws
     * @Title: queryList
     * @Description: TODO(通过sql查询得到结果集)
     */
    public static List<CachedRowSet> queryList(String sql, Object... params)
            throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            fillStatement(stmt, params);
            List<CachedRowSet> list = new ArrayList<>();
            boolean moreResultSets = stmt.execute();

            while (moreResultSets) {
                try {
                    rs = stmt.getResultSet();
                    RowSetFactory factory = RowSetProvider.newFactory();
                    CachedRowSet crs = factory.createCachedRowSet();
                    crs.populate(rs);
                    list.add(crs);
                    moreResultSets = stmt.getMoreResults();
                } finally {
                    close(rs);
                }
            }
            return list;
        } finally {
            close(conn, stmt, rs);
        }
    }

    /**
     * @param @param  sql
     * @param @param  params
     * @param @return
     * @param @throws SQLException 设定文件
     * @return CachedRowSet 返回类型
     * @throws
     * @Title: execute
     * @Description: TODO(执行存储过程)
     */
    public static CachedRowSet execute(String sql, Object... params)
            throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareCall(sql);
            fillStatement(stmt, params);
            rs = stmt.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet crs = factory.createCachedRowSet();
            crs.populate(rs);
            return crs;
        } finally {
            close(conn, stmt, rs);
        }
    }

    /**
     * 执行存储过程
     *
     * @param @param  sql
     * @param @param  params
     * @param @return
     * @param @throws SQLException 设定文件
     * @return List<CachedRowSet> 返回类型
     * @throws
     * @Title: execute
     * @Description: TODO(执行存储过程)
     */
    public static List<CachedRowSet> executeList(String sql, Object... params)
            throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            List<CachedRowSet> results = new ArrayList<>();
            stmt = conn.prepareCall(sql);
            fillStatement(stmt, params);
            boolean moreResultSets = stmt.execute();

            while (moreResultSets) {
                try {
                    rs = stmt.getResultSet();
                    RowSetFactory factory = RowSetProvider.newFactory();
                    CachedRowSet crs = factory.createCachedRowSet();
                    crs.populate(rs);
                    results.add(crs);
                    moreResultSets = stmt.getMoreResults();
                } finally {
                    //close(rs);
                }
            }
            if(params[params.length-1].equals(false)){
                params[params.length-1]=stmt.getObject(params.length);
            }
            return results;
        } finally {
            close(conn, stmt, rs);
        }
    }

    /**
     * @param @param  sql
     * @param @param  params
     * @param @return
     * @param @throws SQLException 设定文件
     * @return int 返回类型
     * @throws
     * @Title: executeUpdate
     * @Description: TODO(执行更新存储过程)
     */
    public static int executeUpdate(String sql, Object... params)
            throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }

        CallableStatement stmt = null;
        int rows = 0;

        try {
            stmt = conn.prepareCall(sql);
            fillStatement(stmt, params);
            stmt.execute();
            rows = stmt.getUpdateCount();
        } finally {
            close(conn, stmt, null);
        }

        return rows;
    }

    public static int update(String sql, Object... params) throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();

        if (conn == null) {
            throw new SQLException("Null connection");
        }
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            fillStatement(stmt, params);
            rows = stmt.executeUpdate();

        } finally {
            close(conn, stmt, null);
        }

        return rows;
    }

    public static int insert(String sql, Object... params) throws SQLException, ClassNotFoundException, ConfigurationException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Null connection");
        }
        PreparedStatement stmt = null;
        int generatedKeys = 0;
        ResultSet resultSet = null;
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fillStatement(stmt, params);
            stmt.executeUpdate();
            resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                generatedKeys = resultSet.getInt(1);
            }
            return generatedKeys;
        } finally {
            close(conn, stmt, resultSet);
        }
    }

}
