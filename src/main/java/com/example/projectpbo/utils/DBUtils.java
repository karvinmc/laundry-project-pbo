package com.example.projectbd.utils;

import java.sql.*;

public class DBUtils {
    // TODO: Pastikan mengganti 'demo' dengan nama database pada MySQL
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Create Connection to Mysql Database
     *
     * @return Connection
     */
    public static Connection createConnection() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed !");
            e.printStackTrace();
            throw e;
        }
        return con;
    }

    /**
     * Close database mysql connection
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
