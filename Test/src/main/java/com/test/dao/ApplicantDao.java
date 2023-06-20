package com.test.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.test.database.DbConnection;
import com.test.model.Applicant;

public class ApplicantDao {
	
	Connection connection = null;
	Statement st = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	
	public int saveBooking(Applicant applicantObj, Date createdDate, Timestamp timeStampObj){
		int result = 0;
		String insertQuery = "insert into space_booking(application_number,name,"
				+ "address,nric_number,email,usage_trade,usage_trade_others,home_ph_no,office_ph_no,hand_ph_no,"
				+ "total_amount,booking_date,L1s1,L1s2,L1s3,L1s4,L1s5,L1s6,L1s7,L1s8,L1s9,L1s10,L1s11,"
				+ "L1s12,L1s13,L1s14,L1s15,L1s16,L1s17,L1s18,L1s19,L1s20,L2s1,L2s2,L2s3,L2s4,L2s5,"
				+ "L2s6,L2s7,L2s8,L2s9,L2s10,no_of_days_booked,approval_status,oic_comment,oic_followup_date_time,"
				+ "created_date,created_date_time,booking_month,selected_slots) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, applicantObj.getApplicationNumber());	
			preparedStatement.setString(2, applicantObj.getName());
			preparedStatement.setString(3, applicantObj.getAddress());
			preparedStatement.setString(4, applicantObj.getNricNumber());
			preparedStatement.setString(5, applicantObj.getEmail());
			preparedStatement.setString(6, applicantObj.getUsageTrade());
			preparedStatement.setString(7, applicantObj.getUsageTradeOthers());
			
			preparedStatement.setString(8, applicantObj.getHomePhoneNumber());
			preparedStatement.setString(9, applicantObj.getOfficePhoneNumber());
			preparedStatement.setString(10, applicantObj.getHandPhoneNumber());			
			preparedStatement.setDouble(11, applicantObj.getTotalAmount());;
			preparedStatement.setString(12, applicantObj.getBookingDate());
			preparedStatement.setString(13, applicantObj.getL1s1());
			preparedStatement.setString(14, applicantObj.getL1s2());
			preparedStatement.setString(15, applicantObj.getL1s3());
			preparedStatement.setString(16, applicantObj.getL1s4());
			preparedStatement.setString(17, applicantObj.getL1s5());
			preparedStatement.setString(18, applicantObj.getL1s6());
			preparedStatement.setString(19, applicantObj.getL1s7());
			preparedStatement.setString(20, applicantObj.getL1s8());
			preparedStatement.setString(21, applicantObj.getL1s9());
			preparedStatement.setString(22, applicantObj.getL1s10());
			preparedStatement.setString(23, applicantObj.getL1s11());
			preparedStatement.setString(24, applicantObj.getL1s12());
			preparedStatement.setString(25, applicantObj.getL1s13());
			preparedStatement.setString(26, applicantObj.getL1s14());
			preparedStatement.setString(27, applicantObj.getL1s15());
			preparedStatement.setString(28, applicantObj.getL1s16());
			preparedStatement.setString(29, applicantObj.getL1s17());
			preparedStatement.setString(30, applicantObj.getL1s18());
			preparedStatement.setString(31, applicantObj.getL1s19());
			preparedStatement.setString(32, applicantObj.getL1s20());
			preparedStatement.setString(33, applicantObj.getL2s1());
			preparedStatement.setString(34, applicantObj.getL2s2());
			preparedStatement.setString(35, applicantObj.getL2s3());
			preparedStatement.setString(36, applicantObj.getL2s4());
			preparedStatement.setString(37, applicantObj.getL2s5());
			preparedStatement.setString(38, applicantObj.getL2s6());
			preparedStatement.setString(39, applicantObj.getL2s7());
			preparedStatement.setString(40, applicantObj.getL2s8());
			preparedStatement.setString(41, applicantObj.getL2s9());
			preparedStatement.setString(42, applicantObj.getL2s10());
			preparedStatement.setInt(43, applicantObj.getNoOfDaysBooked());
			preparedStatement.setString(44, applicantObj.getApprovalStatus());
			preparedStatement.setString(45, applicantObj.getOicComment());
			preparedStatement.setString(46, applicantObj.getOicFollowUpdateTime());
			preparedStatement.setDate(47, createdDate);
			preparedStatement.setTimestamp(48, timeStampObj);
			preparedStatement.setInt(49, applicantObj.getBookingMonth());
			preparedStatement.setString(50, applicantObj.getSlectedSlots());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("Successfully inserted into table");
			} else {
				System.out.println("Error while inserting into table");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
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
	
	
	public List<Applicant> getBookingListOfCurrentMonth(
			Date monthStratDate, Date monthEndDate, String email) {

		List<Applicant> listApplicant = new ArrayList<>();

		String selectQuery = "select * from space_booking where created_date >= ? and created_date <= ? and email = ?";
		int count = 0;
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDate(1, monthStratDate);
			preparedStatement.setDate(2, monthEndDate);
			preparedStatement.setString(3, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				count = resultSet.getInt(1);
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println(count);
		return listApplicant;
	}
	
	public long getBookingListOfCurrentMonthByNric(int bookingMonth, String nric) {

		long count = 0;

		String selectQuery = "select count(*) from space_booking where booking_month = ? and nric_number = ?";
		
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, bookingMonth);
			preparedStatement.setString(2, nric);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getLong(1);
		

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println(count);
		return count;
	}
	
	
	public List<Applicant> getAllBookingList() {

		List<Applicant> listApplicant = new ArrayList<>();

		String selectQuery = "select * from space_booking";
		int count = 0;
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println(count);
		return listApplicant;
	}
	
	public long getCount(){
		long count = 0 ;
		String query = "select count(*) from space_booking";
		
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getLong(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return count;
		
	}
	
	public Applicant getApplicationById(String applicationNumber) throws SQLException {
		Applicant ap = new Applicant();
		String selectQuery = "SELECT * FROM space_booking WHERE application_number = ?";
		try {

			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, applicationNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(resultSet.getInt("no_of_days_booked"));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
	            ap.setSlectedSlots(resultSet.getString("selected_slots")); 
	            ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				
			}
		}catch (SQLException e) {
			printSQLException(e);
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return ap;
	}
//	
//	
//	public List<Applicant> getApplicationByApprovalStatus(String approvalStatus) throws SQLException {
//		List<Applicant> statusList = new ArrayList<>();
//		String selectQuery = "SELECT * FROM space_booking WHERE approval_status = ?";
//		try {
//
//			connection = new DbConnection().getConnection();
//			preparedStatement = connection.prepareStatement(selectQuery);
//			preparedStatement.setString(1, approvalStatus);
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				Applicant ap = new Applicant();
//				ap.setApplicationNumber(resultSet.getString("application_number"));
//				ap.setName(resultSet.getString("name"));
//				ap.setAddress(resultSet.getString("address"));
//				ap.setNricNumber(resultSet.getString("nric_number"));
//				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
//				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
//				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
//				ap.setEmail(resultSet.getString("email"));
//				ap.setTotalAmount(resultSet.getDouble("total_amount"));
//				ap.setBookingDate(resultSet.getString("booking_date"));
//				ap.setL1s1(resultSet.getString("L1s1"));
//				ap.setL1s2(resultSet.getString("L1s2"));
//				ap.setL1s3(resultSet.getString("L1s3"));
//				ap.setL1s4(resultSet.getString("L1s4"));
//				ap.setL1s5(resultSet.getString("L1s5"));
//				ap.setL1s6(resultSet.getString("L1s6"));
//				ap.setL1s7(resultSet.getString("L1s7"));
//				ap.setL1s8(resultSet.getString("L1s8"));
//				ap.setL1s9(resultSet.getString("L1s9"));
//				ap.setL1s10(resultSet.getString("L1s10"));
//				ap.setL1s11(resultSet.getString("L1s11"));
//				ap.setL1s12(resultSet.getString("L1s12"));
//				ap.setL1s13(resultSet.getString("L1s13"));
//				ap.setL1s14(resultSet.getString("L1s14"));
//				ap.setL1s15(resultSet.getString("L1s15"));
//				ap.setL1s16(resultSet.getString("L1s16"));
//				ap.setL1s17(resultSet.getString("L1s17"));
//				ap.setL1s18(resultSet.getString("L1s18"));
//				ap.setL1s19(resultSet.getString("L1s19"));
//				ap.setL1s20(resultSet.getString("L1s20"));
//				ap.setL2s1(resultSet.getString("L2s1"));
//				ap.setL2s2(resultSet.getString("L2s2"));
//				ap.setL2s3(resultSet.getString("L2s3"));
//				ap.setL2s4(resultSet.getString("L2s4"));
//				ap.setL2s5(resultSet.getString("L2s5"));
//				ap.setL2s6(resultSet.getString("L2s6"));
//				ap.setL2s7(resultSet.getString("L2s7"));
//				ap.setL2s8(resultSet.getString("L2s8"));
//				ap.setL2s9(resultSet.getString("L2s9"));
//				ap.setL2s10(resultSet.getString("L2s10"));
//				ap.setNoOfDaysBooked(resultSet.getString("no_of_days_booked"));
//				ap.setApprovalStatus(resultSet.getString("approval_status"));
//				ap.setOicComment(resultSet.getString("oic_comment"));
//				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
//				ap.setCreatedDate(resultSet.getString("created_date"));
//				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
//	ap.setSlectedSlots(resultSet.getString("selected_slots"));
//				statusList.add(ap);
//			}
//		}catch (SQLException e) {
//			printSQLException(e);
//		} finally {
//			try {
//				connection.close();
//				preparedStatement.close();
//				resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return statusList;
//	}
//	
	
//	public boolean updateApplication(Applicant applicant) throws SQLException {
//        String sql = "UPDATE app.space_booking SET approval_status = ?";
//        sql += " WHERE application_number = ?";
//        
//         
//        connection = new DbConnection().getConnection();
//		preparedStatement = connection.prepareStatement(sql);
//        statement.setString(1, book.getTitle());
//        statement.setString(2, book.getAuthor());
//        statement.setFloat(3, book.getPrice());
//        statement.setInt(4, book.getId());
//         
//        boolean rowUpdated = statement.executeUpdate() > 0;
//        statement.close();
//        disconnect();
//        return rowUpdated;     
//    }
	
	
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


	public List<Applicant> getAllListOfBookingMonth(int intMonth) {
		
		List<Applicant> listApplicant = new ArrayList<>();

		String selectQuery = "select * from space_booking where booking_month = ?";
		int count = 0;
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, intMonth);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println(count);
		return listApplicant;
	}


	public List<Applicant> getListOfBookingDate(Date convBookingDate) {
		List<Applicant> listApplicant = new ArrayList<>();

		String selectQuery = "select * from space_booking where booking_date = ?";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDate(1, convBookingDate);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listApplicant;
	}


	public List<Applicant> getListOfUserByBookingDate(Date convBookingDate, String nricNumber) {
		List<Applicant> listApplicant = new ArrayList<>();

		String selectQuery = "select * from space_booking where booking_date = ? and nric_number = ?";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDate(1, convBookingDate);
			preparedStatement.setString(2, nricNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet.getString("usage_trade_others"));
				ap.setTotalAmount(resultSet.getDouble("total_amount"));
				ap.setBookingDate(resultSet.getString("booking_date"));
				ap.setL1s1(resultSet.getString("L1s1"));
				ap.setL1s2(resultSet.getString("L1s2"));
				ap.setL1s3(resultSet.getString("L1s3"));
				ap.setL1s4(resultSet.getString("L1s4"));
				ap.setL1s5(resultSet.getString("L1s5"));
				ap.setL1s6(resultSet.getString("L1s6"));
				ap.setL1s7(resultSet.getString("L1s7"));
				ap.setL1s8(resultSet.getString("L1s8"));
				ap.setL1s9(resultSet.getString("L1s9"));
				ap.setL1s10(resultSet.getString("L1s10"));
				ap.setL1s11(resultSet.getString("L1s11"));
				ap.setL1s12(resultSet.getString("L1s12"));
				ap.setL1s13(resultSet.getString("L1s13"));
				ap.setL1s14(resultSet.getString("L1s14"));
				ap.setL1s15(resultSet.getString("L1s15"));
				ap.setL1s16(resultSet.getString("L1s16"));
				ap.setL1s17(resultSet.getString("L1s17"));
				ap.setL1s18(resultSet.getString("L1s18"));
				ap.setL1s19(resultSet.getString("L1s19"));
				ap.setL1s20(resultSet.getString("L1s20"));
				ap.setL2s1(resultSet.getString("L2s1"));
				ap.setL2s2(resultSet.getString("L2s2"));
				ap.setL2s3(resultSet.getString("L2s3"));
				ap.setL2s4(resultSet.getString("L2s4"));
				ap.setL2s5(resultSet.getString("L2s5"));
				ap.setL2s6(resultSet.getString("L2s6"));
				ap.setL2s7(resultSet.getString("L2s7"));
				ap.setL2s8(resultSet.getString("L2s8"));
				ap.setL2s9(resultSet.getString("L2s9"));
				ap.setL2s10(resultSet.getString("L2s10"));
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listApplicant;
	}
	
	
	public int getBookingCountOfMonth(int month, String nricNumber) {
		List<Applicant> listApplicant = new ArrayList<>();

		int count = 0;
		String selectQuery = "select * from space_booking where booking_month = ? and nric_number = ?";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, month);
			preparedStatement.setString(2, nricNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet.getString("no_of_days_booked")));				
				listApplicant.add(ap);
			}
			for(Applicant obj:listApplicant) {
				if(obj.getNoOfDaysBooked()>0) {
					count += obj.getNoOfDaysBooked();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return count;
	}


	public int getBookingCountByNric(int month, String nricNumber) {
		int count = 0;
		String selectQuery = "select count(*) from space_booking where booking_month = ? and nric_number = ?";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, month);
			preparedStatement.setString(2, nricNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
					count = resultSet.getInt(1);
					System.out.println(count);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return count;
	}
	
	public List<String> getListOfBookingdate(String nricNumber) {
		List<String> listApplicant = new ArrayList<>();
		String selectQuery = "select distinct booking_date from space_booking where nric_number = ?";
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, nricNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
			
				listApplicant.add(resultSet.getString("booking_date"))	;
		        listApplicant.toArray();
			System.out.println(resultSet.getString("booking_date"));
			

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	
		return listApplicant;
	}
}