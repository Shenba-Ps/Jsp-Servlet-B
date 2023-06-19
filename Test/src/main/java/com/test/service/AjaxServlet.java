package com.test.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonToken;
import com.mysql.cj.xdevapi.JsonArray;
import com.test.dao.ApplicantDao;
import com.test.model.Applicant;
import com.test.model.ConvertJSON;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicantService applicantService;
	ApplicantDao applicantDao;

	public AjaxServlet() {
		super();
		this.applicantService = new ApplicantService();
		this.applicantDao = new ApplicantDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-------------------Ajax Servlet----------------------");
		JSONObject objResponseData = new JSONObject();
		ConvertJSON objConvertJSON = new ConvertJSON();
		Applicant applicantObj = new Applicant();

		String strBodyData = request.getParameter("BodyData");
		JSONObject objBodyData = new JSONObject(strBodyData);
		String strAction = objConvertJSON.CJSON_S(objBodyData, "Action").toString();
	 if (strAction.equals("Save Slots")) {
			JSONArray jaBookingList = objBodyData.getJSONArray("BookingData");

			for (int i = 0; i < jaBookingList.length(); i++) {
				applicantObj.setName(objConvertJSON.CJSON_S(objBodyData, "Name"));
				applicantObj.setAddress(objConvertJSON.CJSON_S(objBodyData, "Address"));
				applicantObj.setNricNumber(objConvertJSON.CJSON_S(objBodyData, "NRIC"));
				applicantObj.setEmail(objConvertJSON.CJSON_S(objBodyData, "Email"));
				applicantObj.setUsageTrade(objConvertJSON.CJSON_S(objBodyData, "UsageTrade"));
				applicantObj.setUsageTradeOthers(objConvertJSON.CJSON_S(objBodyData, "Others"));
				applicantObj.setHomePhoneNumber(objConvertJSON.CJSON_S(objBodyData, "Mobile"));
				applicantObj.setOfficePhoneNumber(objConvertJSON.CJSON_S(objBodyData, "OfficeTel"));
				applicantObj.setHandPhoneNumber(objConvertJSON.CJSON_S(objBodyData, "HomeTel"));
				applicantObj.setTotalAmount(Double.valueOf(objConvertJSON.CJSON_S(objBodyData, "TotalAmount")));
				JSONObject objBookingData = jaBookingList.getJSONObject(i);
				String strBookingDate = objConvertJSON.CJSON_S(objBookingData, "BookingDate").toString();
				Date datBookingDate = null;
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					datBookingDate = formatter.parse(strBookingDate);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date convBookingDate = new java.sql.Date(datBookingDate.getTime());
				int intMonth = datBookingDate.getMonth();
				applicantObj.setBookingDate(convBookingDate.toString());
				applicantObj.setBookingMonth(intMonth);
				//Slots
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
				String strUserSelectedSlots = objConvertJSON.CJSON_S(objBookingData, "BookingSlot").toString();
				String[] arrUserSelectedSlots = strUserSelectedSlots.split(",");
				for (int j = 0; j < arrUserSelectedSlots.length; j++) {
					String strSlot = arrUserSelectedSlots[j].toString();
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
				applicantObj.setSlectedSlots(objConvertJSON.CJSON_S(objBookingData, "BookingSlot").toString());
				applicantObj.setNoOfDaysBooked(intUSCount);
				applicantObj.setOicComment("0");
				applicantService.saveBookingDetailService(applicantObj);
			}
		}else if (strAction.equals("Fetch Slots")) {
			applicantObj.setNricNumber(objConvertJSON.CJSON_S(objBodyData, "NRIC"));
			String strBookingDate = objConvertJSON.CJSON_S(objBodyData, "Date").toString();
			Date datBookingDate = null;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				datBookingDate = formatter.parse(strBookingDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date convBookingDate = new java.sql.Date(datBookingDate.getTime());
			int intMonth = datBookingDate.getMonth();

			List<Applicant> listOfUserByBookingDate = applicantDao.getListOfUserByBookingDate(convBookingDate,
					applicantObj.getNricNumber());
			List<Applicant> listOfBookingDate = applicantDao.getListOfBookingDate(convBookingDate);
			int intUserBookingCountForMonth = applicantDao.getBookingCountOfMonth(intMonth,
					applicantObj.getNricNumber());
			System.out.println(intUserBookingCountForMonth);

			String strUserSelectedSlotByDate = "";
			for (Applicant app : listOfUserByBookingDate) {
				if (app.getSlectedSlots() != null) {
					if (strUserSelectedSlotByDate != "") {
						strUserSelectedSlotByDate += ",";
					}
					strUserSelectedSlotByDate += app.getSlectedSlots();
				}
			}
			String strSelectedSlotByDate = "";
			for (Applicant app : listOfBookingDate) {
				if (app.getSlectedSlots() != null) {
					if (strSelectedSlotByDate != "") {
						strSelectedSlotByDate += ",";
					}
					strSelectedSlotByDate += app.getSlectedSlots();
				}
			}

			objResponseData.put("UserBookingCountForMonth", intUserBookingCountForMonth);
			objResponseData.put("UserBookingForDate", strUserSelectedSlotByDate);
			objResponseData.put("SelectedSlotByDate", strSelectedSlotByDate);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(objResponseData.toString());
	}

},
