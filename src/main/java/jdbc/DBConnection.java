package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 10:03
 * @DESC:
 */
public class DBConnection {
    private static String USERNAMR = "h2";
    private static String PASSWORD = "hydeesoft";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@222.244.144.163:5066:hydeebzb";

    // 创建一个数据库连接
    protected static Connection con = null;

    /**
     * 获取Connection对象
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRVIER);
            con = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return con;
    }





}
