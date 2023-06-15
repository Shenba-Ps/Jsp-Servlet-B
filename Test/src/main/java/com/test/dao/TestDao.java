package com.test.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.database.DbConnection;

public class TestDao {

	Connection connection = null;
	Statement st = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public int insertCallLogInformation( String name, String applicationNumber,Date date)
			throws SQLException {
        String email = "tnd@gmail.com";
//        Date strdate= '2023-06-30';
		int result = 0;
		String insertQuery = "insert into app.test(name,app_no,email,created_date) "
				+ "values(?,?,?,?)";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, name);	
			preparedStatement.setString(2, applicationNumber);
			preparedStatement.setString(3, email);
			preparedStatement.setDate(4, date);
		
			System.out.println(applicationNumber +" "+ date);
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("Successfully inserted into table");
			} else {
				System.out.println("Error while inserting into table");
			}

		} catch (SQLException e) {
			printSQLException(e);

		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}

		}
		return result;
	}
	
	
	public int getBookingCountOfTheCurrentMonth(Date monthStratDate,Date monthEndDate,String email){
		String selectQuery = "select count(*) from app.test where created_date >= ? and created_date <= ? and email = ?";
		int count = 0;
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDate(1, monthStratDate);
			preparedStatement.setDate(2, monthEndDate);
			preparedStatement.setString(3, email);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
			count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
		return count;
	}
	
	public long getCount(){
		long count = 0 ;
		String query = "select count(*) from app.test";
		
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getLong(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	
	
	
	
	
	
public void createDb() throws SQLException {
		
		connection = new DbConnection().getConnection();
		try {
			st = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	String createtestTableQuery =	"create table app.test(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(20),PRIMARY KEY (id))";
		String query = " CREATE TABLE app.call_logs ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				+ "name varchar(50) DEFAULT NULL,"
				+ "block varchar(20) DEFAULT NULL,"
				+ "address varchar (50) DEFAULT NULL,"
				+ "door_number varchar(10) DEFAULT NULL,"
				+ "unit_number varchar(10) DEFAULT NULL,"
				+ "postalcode varchar(10) DEFAULT NULL,"
				+ "ic_type varchar(20) DEFAULT NULL,"
				+ "ic_number varchar(20) DEFAULT NULL,"
				+ "email varchar (50) DEFAULT NULL,"
				+ "home_ph_no varchar (10) DEFAULT NULL,"
				+ "office_ph_no varchar (10) DEFAULT NULL,"
				+ "hand_ph_no varchar (10) DEFAULT NULL,"
				+ "pg varchar (10) DEFAULT NULL,"
				+ "fax varchar (10) DEFAULT NULL,"
				+ "nature_of_enquiry varchar(50)  DEFAULT NULL,"
				+ "name_of_enquiry varchar (50) DEFAULT NULL,"
				+ "type_of_enquiry varchar (50) DEFAULT NULL,"
				+ "enquiry_comments varchar (500) DEFAULT NULL,"
				+ "oic varchar (20) DEFAULT NULL,"
				+ "rating varchar (10) DEFAULT NULL,"
				+ "rating_type varchar (10) DEFAULT NULL,"
				+ "details_of_encounter varchar (500) DEFAULT NULL,"
				+ "created_date DATE DEFAULT NULL,"
				+ "created_date_time timestamp DEFAULT NULL,"
				+ "PRIMARY KEY (id)" + ")";

      String insertQuery = "insert into app.test(name,app_no,email,created_date) "
				+ "values('test','123','tnd@gmail.com','2023-05-30')";
		String alterQuery = "ALTER TABLE app.test ADD email VARCHAR(50) DEFAULT NULL";
		String dropQuery = "drop table app.call_logs";
		int rows;
		try {
			rows = st.executeUpdate(insertQuery);
			if (rows > 0) {
				System.out.println("Table successfully created");
			}else{
				System.out.println("Failed to create");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			connection.close();
		}
}
}
