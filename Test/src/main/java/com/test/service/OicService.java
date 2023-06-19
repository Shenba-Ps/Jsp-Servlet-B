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

	String rejectedStatus = "Rejected";	
	String approvedStatus = "Approved";
	long millis = System.currentTimeMillis();
	java.sql.Date oicFollowUpdate = new java.sql.Date(millis);
	public void updateRejectedStatus(Applicant app) throws ParseException {		
        app.setApprovalStatus(rejectedStatus);		
		oicDao.updateAppStatus(app,oicFollowUpdate);
		
		
	}
	public void updateApprovalStatus(Applicant applicant) {
		applicant.setApprovalStatus(approvedStatus);
		
		oicDao.updateApprovalStatus(applicant,oicFollowUpdate);
		
	}
}
