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

public class OicDao {

	Connection connection = null;
	Statement st = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private int noOfRecords;
	
	public List<Applicant> getApplicationByApprovalStatusPagination(
			int currentPage, int recordsPerPage, String approvalStatus)
			throws SQLException {
		List<Applicant> statusList = new ArrayList<>();
		String selectQuery = "SELECT * FROM app.space_booking\n";
		String whereClause = "WHERE approval_status = ?";
		String limitClause = "offset ? rows fetch first ? rows only";
		String countQuery = "select count(*) from app.space_booking WHERE approval_status = ?";
		try {
			String statementStr = "";
			statementStr += selectQuery;
			statementStr += whereClause;
			statementStr += limitClause;
			connection = new DbConnection().getConnection();
			// connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(statementStr);
			preparedStatement.setString(1, approvalStatus);
			preparedStatement.setInt(2, currentPage);
			preparedStatement.setInt(3, recordsPerPage);
			resultSet = preparedStatement.executeQuery();
			connection.commit();
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
				ap.setNoOfDaysBooked(resultSet.getInt("no_of_days_booked"));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet
						.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				statusList.add(ap);
			}
			
			resultSet.close();
			preparedStatement = connection.prepareStatement(countQuery);
			preparedStatement.setString(1, approvalStatus);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				this.noOfRecords = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			printSQLException(e);
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusList;
	}
	public List<Applicant> getApplicationByApprovalStatus(String approvalStatus) throws SQLException {
	List<Applicant> statusList = new ArrayList<>();
	String selectQuery = "SELECT * FROM space_booking WHERE approval_status = ?";
	try {

		connection = new DbConnection().getConnection();
		preparedStatement = connection.prepareStatement(selectQuery);
		preparedStatement.setString(1, approvalStatus);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Applicant ap = new Applicant();
			boolean isDuplicate=false;
			for(Applicant app:statusList)
			{
				if(app.getApplicationNumber().equals(resultSet.getString("application_number")))
				{
					isDuplicate=true;
					app.setBookingDate(app.getBookingDate()+","+resultSet.getString("booking_date"));
					app.setSlectedSlots(app.getSlectedSlots()+"-"+resultSet.getString("selected_slots"));
					break;
				}
			}
			if(isDuplicate)
			{
				continue;
			}
			ap.setApplicationNumber(resultSet.getString("application_number"));
			ap.setName(resultSet.getString("name"));
			ap.setAddress(resultSet.getString("address"));
			ap.setNricNumber(resultSet.getString("nric_number"));
			ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
			ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
			ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
			ap.setEmail(resultSet.getString("email"));
			ap.setUsageTrade(resultSet.getString("usage_trade"));
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
			ap.setNoOfDaysBooked(resultSet.getInt("no_of_days_booked"));
			ap.setApprovalStatus(resultSet.getString("approval_status"));
			ap.setOicComment(resultSet.getString("oic_comment"));
			ap.setOicFollowUpdateTime(resultSet.getString("oic_followup_date_time"));
			ap.setCreatedDate(resultSet.getString("created_date"));
			ap.setCreatedDateTime(resultSet.getString("created_date_time"));
            ap.setSlectedSlots(resultSet.getString("selected_slots"));
			statusList.add(ap);
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
	return statusList;
}
	public List<Applicant> getAllBookingList(int currentPage, int recordsPerPage) {

		List<Applicant> listApplicant = new ArrayList<>();
		int start = currentPage * recordsPerPage - recordsPerPage;
		 String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM space_booking LIMIT "
	        		+ currentPage + ", " + recordsPerPage;
	        String count = "SELECT FOUND_ROWS()";
	

		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				boolean isDuplicate=false;
				for(Applicant app:listApplicant)
				{
					if(app.getApplicationNumber().equals(resultSet.getString("application_number")))
					{
						isDuplicate=true;
						app.setNoOfDaysBooked(app.getNoOfDaysBooked()+1);
						app.setBookingDate(app.getBookingDate()+","+resultSet.getString("booking_date"));
						
						break;
					}
				}
				if(isDuplicate)
				{
					continue;
				}
				ap.setApplicationNumber(resultSet.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
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
//			resultSet.close();
			preparedStatement = connection.prepareStatement(count);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			      this.noOfRecords = resultSet.getInt(1);
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
	
	public List<Applicant> getAllBookingListPagination(int currentPage,
			int recordsPerPage) {

		List<Applicant> listApplicant = new ArrayList<>();
		String selectClause = "select * from app.space_booking\n";
		String limitClause = "offset ? rows fetch first ? rows only";
		String countQuery = "select count(*) from app.space_booking";
		int count = 0;
		try {
			String statementStr = "";
			statementStr += selectClause;
			statementStr += limitClause;
			connection = new DbConnection().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(statementStr);
			preparedStatement.setInt(1, currentPage);
			preparedStatement.setInt(2, recordsPerPage);
			resultSet = preparedStatement.executeQuery();
			connection.commit();
			while (resultSet.next()) {
				Applicant ap = new Applicant();
				ap.setApplicationNumber(resultSet
						.getString("application_number"));
				ap.setName(resultSet.getString("name"));
				ap.setAddress(resultSet.getString("address"));
				ap.setNricNumber(resultSet.getString("nric_number"));
				ap.setHomePhoneNumber(resultSet.getString("home_ph_no"));
				ap.setOfficePhoneNumber(resultSet.getString("office_ph_no"));
				ap.setHandPhoneNumber(resultSet.getString("hand_ph_no"));
				ap.setEmail(resultSet.getString("email"));
				ap.setUsageTrade(resultSet.getString("usage_trade"));
				ap.setUsageTradeOthers(resultSet
						.getString("usage_trade_others"));
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
				ap.setNoOfDaysBooked(Integer.parseInt(resultSet
						.getString("no_of_days_booked")));
				ap.setApprovalStatus(resultSet.getString("approval_status"));
				ap.setOicComment(resultSet.getString("oic_comment"));
				ap.setOicFollowUpdateTime(resultSet
						.getString("oic_followup_date_time"));
				ap.setCreatedDate(resultSet.getString("created_date"));
				ap.setCreatedDateTime(resultSet.getString("created_date_time"));
				ap.setBookingMonth(Integer.parseInt(resultSet
						.getString("booking_month")));
				ap.setSlectedSlots(resultSet.getString("selected_slots"));
				listApplicant.add(ap);

			}
			resultSet.close();
			preparedStatement = connection.prepareStatement(countQuery);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				this.noOfRecords = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println(count);
		return listApplicant;
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
	
	public Integer getNumberOfRows() {

        return noOfRecords;
    }
	
	
	public int updateAppStatus(Applicant applicantObj, Date createdDate, Timestamp timestamp, Date oicFollowUpdate) {
		
		int count = 0;
		String updateQuery = "UPDATE space_booking set application_number = ?,name = ?,"
				+ "address = ?,nric_number = ?,email = ?,usage_trade = ?,usage_trade_others = ?,home_ph_no = ?,office_ph_no = ?,hand_ph_no = ?,"
				+ "total_amount = ?,booking_date = ?,L1s1 = ?,L1s2 = ?,L1s3 = ?,L1s4 = ?,L1s5 = ?,L1s6 = ?,L1s7 = ?,L1s8 = ?,L1s9 = ?,L1s10 = ?,L1s11 = ?,"
				+ "L1s12 = ?,L1s13 = ?,L1s14 = ?,L1s15 = ?,L1s16 = ?,L1s17 = ?,L1s18 = ?,L1s19 = ?,L1s20 = ?,L2s1 = ?,L2s2 = ?,L2s3 = ?,L2s4 = ?,L2s5 = ?,"
				+ "L2s6 = ?,L2s7 = ?,L2s8 = ?,L2s9 = ?,L2s10 = ?,no_of_days_booked = ?,approval_status = ?,oic_comment = ?,oic_followup_date_time = ?,"
				+ "created_date = ?,created_date_time = ?,booking_month = ?,selected_slots= ? where application_number = ? ";
		
		
		try {
			connection = new DbConnection().getConnection();
			preparedStatement = connection.prepareStatement(updateQuery);
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
			preparedStatement.setDate(46, oicFollowUpdate);
			preparedStatement.setDate(47, createdDate);
			preparedStatement.setTimestamp(48, timestamp);
			preparedStatement.setInt(49, applicantObj.getBookingMonth());
			preparedStatement.setString(50, applicantObj.getSlectedSlots());
			count = preparedStatement.executeUpdate();
			if(count > 0) {
				System.out.println("upadted");
			}else {
				System.out.println("failed");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return count;
		
	}
}
