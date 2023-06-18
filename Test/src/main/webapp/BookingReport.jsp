<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Booking Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Plugins/Boostrap.css">
<script src="Plugins/jQuery.js"></script>
<script src="Plugins/bootstrap.js"></script>
<script src="Script/BookingReport.js"></script>
</head>
<body>
	<div style="height: 97vh; background-color: #4169E1;">
		<div>Report</div>
		
		<table>
			<thead>
				<tr class="ListHeaderRow">
					<th class="ListHeaderCol" style="width:200px;">Application.NO</th>
					<th class="ListHeaderCol">Name of Applicant</th>
					<th class="ListHeaderCol">Trade Description</th>
					<th class="ListHeaderCol">Total Days booked</th>
					<th class="ListHeaderCol">Date of Booking</th>
					<th class="ListHeaderCol">Status</th>
					<th class="ListHeaderCol">Date of Case Submitted</th>
					<th class="ListHeaderCol">Last Date of Follow-up by OIC</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="bookingList" items="${allBookingList}"
					varStatus="counter">

					<tr class="ListBodyRow">

						<td class="ListBodyCol" style="width:200px;"><c:out
								value="${bookingList.applicationNumber}" /></td>

						<td class="ListBodyCol"><c:out value="${bookingList.name}" /></td>
						<td class="ListBodyCol"><c:out value="${bookingList.usageTrade}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.noOfDaysBooked}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.bookingDate}" /></td>
						<td class="ListBodyCol"><c:out value="${bookingList.approvalStatus}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.createdDate}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.oicFollowUpdateTime}" /></td>
					

					
					</tr>

				</c:forEach>



			</tbody>
		</table>

		<c:if test="${currentPage != 1}">
			<td><a href="bookingList?page=${currentPage - 1}">Previous</a></td>
		</c:if>

		<%--For displaying Page numbers. The when condition does not display
              a link for the current page--%>

		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a href="bookingList?page=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>