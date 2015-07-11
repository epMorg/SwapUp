package ct869.project.model;

import java.sql.*;
//import java.util.*;

public class ConnectionManager {
	static Connection conn;
	static String url;
	static String user;
	static String password;
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/project";
			String user = "root";
			String password = "Bloodsport123!";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			try {
				conn = DriverManager.getConnection(url, user, password);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}

}
