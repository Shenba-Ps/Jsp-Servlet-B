package com.test.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.test.dao.OicDao;
import com.test.model.Applicant;

public class OicService {

	private OicDao oicDao;
	
	public OicService() {
		super();
		this.oicDao = new OicDao();
	}

	public void updateStatus(Applicant app) throws ParseException {
		Applicant newApp = new Applicant();
		String status = "Rejected";
		
		long millis = System.currentTimeMillis();
		java.sql.Date oicFollowUpdate = new java.sql.Date(millis);
		
		newApp.setAddress(app.getAddress());
		newApp.setApplicationNumber(app.getApplicationNumber());
		newApp.setApprovalStatus(status);
		newApp.setBookingDate(app.getBookingDate());
		newApp.setBookingMonth(app.getBookingMonth());
		
		
		 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date datBookingDate = formatter.parse(newApp.getCreatedDate());
		java.sql.Date convCreatedDate = new java.sql.Date(datBookingDate.getTime());
		
		SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date = formatter.parse(app.getCreatedDateTime());
        java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
		
		newApp.setCreatedDateTime(app.getCreatedDateTime());
		newApp.setEmail(app.getEmail());
		newApp.setHandPhoneNumber(app.getHandPhoneNumber());
		newApp.setHomePhoneNumber(app.getHomePhoneNumber());
		newApp.setName(app.getName());
		newApp.setNoOfDaysBooked(app.getNoOfDaysBooked());
		newApp.setNricNumber(app.getNricNumber());
		newApp.setOfficePhoneNumber(app.getOfficePhoneNumber());
		newApp.setOicComment(app.getOicComment());
		newApp.setSlectedSlots(app.getSlectedSlots());
		newApp.setTotalAmount(app.getTotalAmount());
		newApp.setUsageTrade(app.getUsageTrade());
		newApp.setUsageTradeOthers(app.getUsageTradeOthers());
		newApp.setL1s1(app.getL1s1());
		newApp.setL1s2(app.getL1s2());
		newApp.setL1s3(app.getL1s3());
		newApp.setL1s4(app.getL1s4());
		newApp.setL1s5(app.getL1s5());
		newApp.setL1s6(app.getL1s6());
		newApp.setL1s7(app.getL1s7());
		newApp.setL1s8(app.getL1s8());
		newApp.setL1s9(app.getL1s9());
		newApp.setL1s10(app.getL1s10());
		newApp.setL1s11(app.getL1s11());
		newApp.setL1s12(app.getL1s12());
		newApp.setL1s13(app.getL1s13());
		newApp.setL1s14(app.getL1s14());
		newApp.setL1s15(app.getL1s15());
		newApp.setL1s16(app.getL1s16());
		newApp.setL1s17(app.getL1s17());
		newApp.setL1s18(app.getL1s18());
		newApp.setL1s19(app.getL1s19());
		newApp.setL1s20(app.getL1s20());
		newApp.setL2s1(app.getL2s1());
		newApp.setL2s2(app.getL2s2());
		newApp.setL2s3(app.getL2s3());
		newApp.setL2s4(app.getL2s4());
		newApp.setL2s5(app.getL2s5());
		newApp.setL2s6(app.getL2s6());
		newApp.setL2s7(app.getL2s7());
		newApp.setL2s8(app.getL2s8());
		newApp.setL2s9(app.getL2s9());
		newApp.setL2s10(app.getL2s10());
		
		oicDao.updateAppStatus(newApp,convCreatedDate,timeStampDate,oicFollowUpdate);
		
	}
}
