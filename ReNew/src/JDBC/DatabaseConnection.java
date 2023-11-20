package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	// DB에 대한 접속을 설정
	// DB Driver, URL, user, password
	// com.musql.cj.jdbc.Driver
	// url : jdbc.musql://localhost:3306/db명(javadb)
	// user : javaUser
	// password : mysql
	
	private static DatabaseConnection dbc = new DatabaseConnection();
	private Connection conn = null;
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://localhost:3306/javadb";
	
	private DatabaseConnection() {
		try {
			Class.forName(jdbcDriver);  // Drive를 로드하기위한 규격
			conn = DriverManager.getConnection(jdbcUrl, "javaUser", "mysql");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보가 정확하지 않습니다.");
			e.printStackTrace();
		}
	}

	public static DatabaseConnection getInstance() {
		return dbc;
		
	}
	
	public Connection getConnection() {
		return conn;
	}
}
