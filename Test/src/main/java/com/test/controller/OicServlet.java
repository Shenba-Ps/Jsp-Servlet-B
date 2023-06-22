package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.ApplicantDao;
import com.test.dao.OicDao;
import com.test.model.Applicant;
import com.test.service.OicService;

@WebServlet(name = "OicServlet", urlPatterns = { "/pendingList", "/reject", "/approval", "/report" })
public class OicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private OicDao oicDao; 
     private ApplicantDao applicantDao;
     private OicService oicService;
   
    public OicServlet() {
        super();
        this.oicDao = new OicDao();
        this.applicantDao = new ApplicantDao();
        this.oicService = new OicService();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    System.out.println("Hi");
		String url = request.getServletPath();
		System.out.println("url"+url);
		String status = "In-Progress";
		String appNo1 = request.getParameter("appInput");
		System.out.println("----------"+appNo1);
		if(url.equals("/pendingList")) {
		
		try {
			List<Applicant> pendingStatusList = oicDao.getApplicationByApprovalStatus(status);
			request.setAttribute("pendingStatusList", pendingStatusList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookingApprovalT.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
		else if(url.equals("/reject")) {
			System.out.println("/reject");
			String appNo = request.getParameter("applicationNumber");
			String oicComments = request.getParameter("nmReason");
			System.out.println("----------------------------------------------------------------"+appNo);
			System.out.println("----------------------------------------------------------------"+oicComments);
		}
		else if(url.equals("/approval")) {
			System.out.println("----------------appno----------------------");
			String appNo = request.getParameter("appInput");
			System.out.println(appNo);
			try {
				Applicant applicant = applicantDao.getApplicationById(appNo);
				oicService.updateStatus(applicant);
				List<Applicant> pendingStatusList = oicDao.getApplicationByApprovalStatus(status);
				request.setAttribute("pendingStatusList", pendingStatusList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("BookingApproval.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(url.equals("/report")) {
			 int page = 1;
		        int recordsPerPage = 10;
		        if(request.getParameter("page") != null)
		            page = Integer.parseInt(request.getParameter("page"));
			List<Applicant> allBookingList = oicDao.getAllBookingList((page-1)*recordsPerPage,
                    recordsPerPage);
			request.setAttribute("allBookingList", allBookingList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookingReport.jsp");
			dispatcher.forward(request, response);
		}
	}

}
