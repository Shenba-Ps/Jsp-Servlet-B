package com.test.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.ApplicantDao;
import com.test.model.Applicant;
import com.test.service.ApplicantService;

@WebServlet(name = "ApplicantServlet", urlPatterns = { "/saveBooking", "/list" })
public class ApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicantService applicantService;
	ApplicantDao applicantDao;

	public ApplicantServlet() {
		super();
		this.applicantService = new ApplicantService();
		this.applicantDao = new ApplicantDao();
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET trigerred");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST trigerred");
		String url = request.getServletPath();

		Applicant applicantObj = new Applicant();
		String strUserSelectedSlots = request.getParameter("nmSelectedSlots");// nmTotalAmount
		String[] arrUserSelectedSlots = strUserSelectedSlots.split(",");
		String strdate = request.getParameter("nmBookingDate");

		Date datBookingDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			datBookingDate = formatter.parse(strdate);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		java.sql.Date convBookingDate = new java.sql.Date(datBookingDate.getTime());
		int intMonth = datBookingDate.getMonth();

		applicantObj.setName(request.getParameter("nmName"));
		applicantObj.setAddress(request.getParameter("nmAddress"));
		applicantObj.setNricNumber(request.getParameter("nmNRIC"));
		applicantObj.setEmail(request.getParameter("nmEmail"));
		applicantObj.setUsageTrade(request.getParameter("UsageTrade"));
		applicantObj.setUsageTradeOthers(request.getParameter("nmOthers"));
		applicantObj.setHomePhoneNumber(request.getParameter("nmHome"));
		applicantObj.setOfficePhoneNumber(request.getParameter("nmOffice"));
		applicantObj.setHandPhoneNumber(request.getParameter("nmMobile"));
		applicantObj.setTotalAmount(Double.valueOf("0"));
		applicantObj.setBookingDate(request.getParameter("nmBookingDate"));
		applicantObj.setSlectedSlots(strUserSelectedSlots);
		System.out.println(applicantObj.getBookingDate());
		applicantObj.setBookingMonth(intMonth);
		if (url.equals("/saveBooking")) {
			applicantObj.setL1s1("0");
			applicantObj.setL1s2("0");
			applicantObj.setL1s3("0");
			applicantObj.setL1s4("0");
			applicantObj.setL1s5("0");
			applicantObj.setL1s6("0");
			applicantObj.setL1s7("0");
			applicantObj.setL1s8("0");
			applicantObj.setL1s9("0");
			applicantObj.setL1s10("0");
			applicantObj.setL1s11("0");
			applicantObj.setL1s12("0");
			applicantObj.setL1s13("0");
			applicantObj.setL1s14("0");
			applicantObj.setL1s15("0");
			applicantObj.setL1s16("0");
			applicantObj.setL1s17("0");
			applicantObj.setL1s18("0");
			applicantObj.setL1s19("0");
			applicantObj.setL1s20("0");
			applicantObj.setL2s1("0");
			applicantObj.setL2s2("0");
			applicantObj.setL2s3("0");
			applicantObj.setL2s4("0");
			applicantObj.setL2s5("0");
			applicantObj.setL2s6("0");
			applicantObj.setL2s7("0");
			applicantObj.setL2s8("0");
			applicantObj.setL2s9("0");
			applicantObj.setL2s10("0");
			int intUSCount = 0;
			for (int i = 0; i < arrUserSelectedSlots.length; i++) {
				String strSlot = arrUserSelectedSlots[i].toString();
				intUSCount++;
				if (strSlot.equals("L1S1")) {
					applicantObj.setL1s1("1");
				} else if (strSlot.equals("L1S2")) {
					applicantObj.setL1s2("1");
				} else if (strSlot.equals("L1S3")) {
					applicantObj.setL1s3("1");
				} else if (strSlot.equals("L1S4")) {
					applicantObj.setL1s4("1");
				} else if (strSlot.equals("L1S5")) {
					applicantObj.setL1s5("1");
				} else if (strSlot.equals("L1S6")) {
					applicantObj.setL1s6("1");
				} else if (strSlot.equals("L1S7")) {
					applicantObj.setL1s7("1");
				} else if (strSlot.equals("L1S8")) {
					applicantObj.setL1s8("1");
				} else if (strSlot.equals("L1S9")) {
					applicantObj.setL1s9("1");
				} else if (strSlot.equals("L1S10")) {
					applicantObj.setL1s10("1");
				} else if (strSlot.equals("L1S11")) {
					applicantObj.setL1s11("1");
				} else if (strSlot.equals("L1S12")) {
					applicantObj.setL1s12("1");
				} else if (strSlot.equals("L1S13")) {
					applicantObj.setL1s13("1");
				} else if (strSlot.equals("L1S14")) {
					applicantObj.setL1s14("1");
				} else if (strSlot.equals("L1S15")) {
					applicantObj.setL1s15("1");
				} else if (strSlot.equals("L1S16")) {
					applicantObj.setL1s16("1");
				} else if (strSlot.equals("L1S17")) {
					applicantObj.setL1s17("1");
				} else if (strSlot.equals("L1S18")) {
					applicantObj.setL1s18("1");
				} else if (strSlot.equals("L1S19")) {
					applicantObj.setL1s19("1");
				} else if (strSlot.equals("L1S20")) {
					applicantObj.setL1s20("1");
				} else if (strSlot.equals("L2S1")) {
					applicantObj.setL2s1("1");
				} else if (strSlot.equals("L2S2")) {
					applicantObj.setL2s2("1");
				} else if (strSlot.equals("L2S3")) {
					applicantObj.setL2s3("1");
				} else if (strSlot.equals("L2S4")) {
					applicantObj.setL2s4("1");
				} else if (strSlot.equals("L2S5")) {
					applicantObj.setL2s5("1");
				} else if (strSlot.equals("L2S6")) {
					applicantObj.setL2s6("1");
				} else if (strSlot.equals("L2S7")) {
					applicantObj.setL2s7("1");
				} else if (strSlot.equals("L2S8")) {
					applicantObj.setL2s8("1");
				} else if (strSlot.equals("L2S9")) {
					applicantObj.setL2s9("1");
				} else if (strSlot.equals("L2S10")) {
					applicantObj.setL2s10("1");
				}
			}
			applicantObj.setNoOfDaysBooked(intUSCount);
			applicantObj.setOicComment("0");
			applicantService.saveBookingDetailService(applicantObj);
			response.sendRedirect("Booking.jsp");
		} else if (url.equals("/list")) {
			// List<Applicant> listAllBooking = applicantDao.getAllBookingList();
			// List<Applicant> listOfBookingMonth =
			// applicantDao.getAllListOfBookingMonth(intMonth);
			long intUserBookingCountForMonth = applicantDao.getBookingListOfCurrentMonthByNric(intMonth,
					applicantObj.getNricNumber());
			List<Applicant> listOfUserByBookingDate = applicantDao.getListOfUserByBookingDate(convBookingDate,
					applicantObj.getNricNumber());
			List<Applicant> listOfBookingDate = applicantDao.getListOfBookingDate(convBookingDate);
			int count = applicantDao.getBookingCountOfMonth(intMonth, applicantObj.getNricNumber());
			System.out.println(count);

			String strUserSelectedSlotByDate = null;
			for (Applicant app : listOfUserByBookingDate) {
				if (app.getSlectedSlots() != null) {
					if (strUserSelectedSlotByDate != "") {
						strUserSelectedSlotByDate += ",";
					}
					strUserSelectedSlotByDate += app.getSlectedSlots();
				}
			}
			String strSelectedSlotByDate = null;
			for (Applicant app : listOfBookingDate) {
				if (app.getSlectedSlots() != null) {
					if (strSelectedSlotByDate != "") {
						strSelectedSlotByDate += ",";
					}
					strSelectedSlotByDate += app.getSlectedSlots();
				}
			}

			request.setAttribute("hdnAction", "Book Slot");
			request.setAttribute("atrName", request.getParameter("nmName"));
			request.setAttribute("atrAddress", request.getParameter("nmAddress"));
			request.setAttribute("atrNRIC", request.getParameter("nmNRIC"));
			request.setAttribute("atrEmail", request.getParameter("nmEmail"));
			request.setAttribute("atrUsageTrade", request.getParameter("UsageTrade"));
			request.setAttribute("atrOthers", request.getParameter("nmOthers"));
			request.setAttribute("atrHome", request.getParameter("nmHome"));
			request.setAttribute("atrOffice", request.getParameter("nmOffice"));
			request.setAttribute("atrMobile", request.getParameter("nmMobile"));
			request.setAttribute("atrBookingDate", request.getParameter("nmBookingDate"));
			request.setAttribute("atrUserBookingCountForMonth", count);
			request.setAttribute("atrUserBookingForDate", strUserSelectedSlotByDate);
			request.setAttribute("atrSelectedSlotByDate", strSelectedSlotByDate);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Booking.jsp");
			dispatcher.forward(request, response);
		}
	}
}