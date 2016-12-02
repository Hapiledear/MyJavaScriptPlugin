package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private static String url = "jdbc:oracle:thin:@172.16.135.251:1521:bap";
	private static String driver = "oracle.jdbc.driver.OracleDriver";

	private static String user = "cfs";
	private static String password = "cfs";

	protected Connection conn;
	protected PreparedStatement pstm;
	protected ResultSet rs;

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url, user,
					password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public void closeAll(Connection conn, Statement stms, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 若Statement对象不为空，则关闭
		if (stms != null) {
			try {
				stms.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 若数据库连接对象不为空，则关闭
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
