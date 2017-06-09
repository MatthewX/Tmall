package tmall.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
	
	private static String ip = "127.0.0.1";
	private static int port = 3306;
	private static String database = "tmall";
	private static String encoding = "UTF-8";
	private static String loginName = "root";
	private static String password = "56422753";
    
	static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		return (Connection) DriverManager.getConnection(url, loginName, password);
	}
}
