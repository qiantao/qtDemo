package com.qt.jdbc;

import java.sql.*;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 10:03
 * @DESC:
 */
public class MsqlDBConnection {
    private static String USERNAMR = "root";
//    private static String USERNAMR = "root";
    private static String PASSWORD = "Perfma888.";
//    private static String DRVIER = "com.mysql.jdbc.Driver";
    private static String DRVIER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://10.10.16.105:3306/mdl";

//    private static String URL = "jdbc:mysql://10.10.16.207:3306/perfma_xsea";
    protected static Connection con = null;

    /**
     * 获取Connection对象
     *
     * @return
     */
    public static Connection getConnection() {

        return getConnection(0);
    }
    public static Connection getConnection(int type) {
        String url =URL;
        try {
            Class.forName(DRVIER);
            if(con==null) {
                con = DriverManager.getConnection(url, USERNAMR, PASSWORD);
                System.out.println("成功连接数据库");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }
        return con;
    }
    public static void closeCon() {
        con = getConnection();
        try {
            if (con != null) con.close();
        } catch (Exception e) {

        }
        ;
    }

    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        if(con!=null) con.close();
        if(pstmt!=null) pstmt.close();
        if(rs!=null) rs.close();
    }
    public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
        close(con,pstmt,null);

    }
    public static void close(Connection con) throws SQLException {
        close(con,null,null);
    }

    public static void close() throws SQLException {
        close(con,null,null);
    }




}
