<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Booking Approval</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Plugins/Boostrap.css">
<link rel="stylesheet" href="Script/Common.css">
<script src="Plugins/jQuery.js"></script>
<script src="Plugins/bootstrap.js"></script>
<script src="Script/BookingApproval.js"></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/approve" method="post">	
	<div style="height: 97vh;">
		<div class="Title form-group">Please approve or Reject the
			booking</div>
		<table>
			<thead>
				<tr class="ListHeaderRow">
					<th class="ListHeaderCol" style="width:200px;">Application.NO</th>
					<th class="ListHeaderCol">Name</th>
					<th class="ListHeaderCol">Address</th>
					<th class="ListHeaderCol">Nric</th>
					<th class="ListHeaderCol">Usage Trade</th>
					<th class="ListHeaderCol">Email</th>
					<th class="ListHeaderCol">Hand Phone</th>
					<th class="ListHeaderCol">Office</th>
					<th class="ListHeaderCol">Home</th>
					<th class="ListHeaderCol">Booking Date</th>
					<th class="ListHeaderCol">Booking Slots</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="bookingList" items="${pendingStatusList}"
					varStatus="counter">

					<tr class="ListBodyRow">

						<td class="ListBodyCol" style="width:200px;"><c:out
								value="${bookingList.applicationNumber}" /></td>

						<td class="ListBodyCol"><c:out value="${bookingList.name}" /></td>
						<td class="ListBodyCol"><c:out value="${bookingList.address}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.nricNumber}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.usageTrade}" /></td>
						<td class="ListBodyCol"><c:out value="${bookingList.email}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.homePhoneNumber}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.officePhoneNumber}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.handPhoneNumber}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.bookingDate}" /></td>
						<td class="ListBodyCol"><c:out
								value="${bookingList.slectedSlots}" /></td>

						<td class="ListBodyCol">
							<button type="button" class="btn btn-error form-group"
								onclick="OpenComments()">Reject</button>
							<button type="button" class="btn btn-primary form-group"
								href="approval?applicationNumber=<c:out value='${bookingList.applicationNumber}' />">Approval</button>
							<input type="hidden" id="hdnRefNo" name="hdnRefNo"
							value="${bookingList.applicationNumber}"></input>
						</td>
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

		<%--For displaying Next link --%>

		<c:if test="${currentPage lt noOfPages}">
			<td><a href="bookingList?page=${currentPage + 1}">Next</a></td>
		</c:if>
	</div>
	<!-- Modal -->
	<div class="modal fade" data-backdrop="static" id="mdlAck"
		role="dialog">
		<div class="modal-dialog" style="width: 70%;">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<div class="row form-group">
						<div class="col-xs-3 RightAlign">
							<label>Comments</label>
						</div>
						<div class="col-xs-6">
							<input class="form-control" type="text" id="idComments"
								name="nmComments">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="onclickReject()">Reject</button>
					<button type="submit" class="btn btn-primary" id="btnReject"
						style="display: none;"
						formaction="<%=request.getContextPath()%>/reject"></button>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>