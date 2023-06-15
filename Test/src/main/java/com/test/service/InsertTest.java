package com.test.service;



import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.test.dao.TestDao;



public class InsertTest {

	private TestDao testDao;
	public InsertTest() {
		super();
		this.testDao = new TestDao();
	}
	
	public void insertdata(String name){
		
		long count = testDao.getCount();
		long countVar = count + 1 ;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String appNumber = "Slot"+"-"+date+"-"+countVar;
		System.out.println(appNumber);
		try {
			testDao.insertCallLogInformation(name,appNumber,date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void getCountofBooking(){
		LocalDate todaydate = LocalDate.now();
		System.out.println("Months first date in yyyy-mm-dd: " +todaydate.withDayOfMonth(1));
		
		LocalDate now = LocalDate.now(); 
		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth()); 
		java.sql.Date sqlLastDate = java.sql.Date.valueOf(lastDay);
		System.out.println(sqlLastDate);
		java.sql.Date sqlStartDate = java.sql.Date.valueOf(todaydate);
		
		String email = "tnd@gmail.com";
		
		testDao.getBookingCountOfTheCurrentMonth(sqlStartDate,sqlLastDate,email);
		
	}
	

}