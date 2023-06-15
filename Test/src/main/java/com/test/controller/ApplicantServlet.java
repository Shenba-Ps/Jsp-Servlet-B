package com.test.controller;



import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.Applicant;
import com.test.service.ApplicantService;


@WebServlet(name = "ApplicantServlet", urlPatterns = { "/saveBooking","/"})
public class ApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ApplicantService applicantService;  

    public ApplicantServlet() {
        super();
        this.applicantService = new ApplicantService();

    }

	
	public void init(ServletConfig config) throws ServletException {

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Applicant applicantObj = new Applicant();
		String url = request.getServletPath();
		applicantObj.setName(request.getParameter("nmName"));
		applicantObj.setAddress(request.getParameter("nmAddress"));
		applicantObj.setNricNumber(request.getParameter("nmNRIC"));
		applicantObj.setEmail(request.getParameter("nmEmail"));
		applicantObj.setUsageTrade(request.getParameter("UsageTrade"));
		applicantObj.setUsageTradeOthers(request.getParameter("nmOthers"));
		applicantObj.setHomePhoneNumber(request.getParameter("nmHome"));
		applicantObj.setOfficePhoneNumber(request.getParameter("nmOffice"));
		applicantObj.setHandPhoneNumber(request.getParameter("nmMobile"));
		applicantObj.setTotalAmount(Double.valueOf(request.getParameter("")));
		applicantObj.setBookingDate(request.getParameter("nmBookingDate"));
		applicantObj.setL1s1(request.getParameter(""));  applicantObj.setL1s2(request.getParameter(""));
		applicantObj.setL1s3(request.getParameter(""));  applicantObj.setL1s4(request.getParameter(""));
		applicantObj.setL1s5(request.getParameter(""));  applicantObj.setL1s6(request.getParameter(""));
		applicantObj.setL1s7(request.getParameter(""));  applicantObj.setL1s8(request.getParameter(""));
		applicantObj.setL1s9(request.getParameter(""));  applicantObj.setL1s10(request.getParameter(""));
		applicantObj.setL1s11(request.getParameter("")); applicantObj.setL1s12(request.getParameter(""));
		applicantObj.setL1s13(request.getParameter("")); applicantObj.setL1s14(request.getParameter(""));
		applicantObj.setL1s15(request.getParameter("")); applicantObj.setL1s16(request.getParameter(""));
		applicantObj.setL1s17(request.getParameter("")); applicantObj.setL1s18(request.getParameter(""));
		applicantObj.setL1s19(request.getParameter("")); applicantObj.setL1s20(request.getParameter(""));
		applicantObj.setL2s1(request.getParameter(""));  applicantObj.setL2s2(request.getParameter(""));
		applicantObj.setL2s3(request.getParameter(""));  applicantObj.setL2s4(request.getParameter(""));
		applicantObj.setL2s5(request.getParameter(""));  applicantObj.setL2s6(request.getParameter(""));
		applicantObj.setL2s7(request.getParameter(""));  applicantObj.setL2s8(request.getParameter(""));
		applicantObj.setL2s9(request.getParameter(""));  applicantObj.setL2s10(request.getParameter(""));
		applicantObj.setNoOfDaysBooked(request.getParameter(""));
		applicantObj.setApprovalStatus(request.getParameter(""));
		applicantObj.setOicComment(request.getParameter(""));
		applicantObj.setOicFollowUpdateTime(request.getParameter(""));
		
		if(url.equals("/saveBooking")){
			applicantService.saveBookingDetailService(applicantObj);
			response.sendRedirect("");
		}
		
		
		
	}

} 