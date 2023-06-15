package com.test.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private String jdbcUrl="jdbc:mysql://localhost:3306/demo?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername="shenba";
	private String jdbcPassword="shenbagarajan";

	public Connection getConnection() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException | SQLException 
				 e) {

			e.printStackTrace();
}
		return connection;
}
	
	}