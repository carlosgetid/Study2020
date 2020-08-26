package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	public static Connection getConnection() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url,user,pass;
			url="jdbc:mysql://localhost:3306/sys_offline_study2020";
			user="root";
			pass="mysql";
			cn=DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
