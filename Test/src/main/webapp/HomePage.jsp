<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>HomePage</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Plugins/Boostrap.css">
<script src="Plugins/jQuery.js"></script>
<script src="Plugins/bootstrap.js"></script>
<script src="Script/Home.js"></script>
</head>
<body>
	<div style="height: 97vh; background-color: #4169E1;">
		<div
			style="padding-left: 25%; padding-right: 25%; padding-top: 10%; padding-top: 10%; text-align: center;">
			<button type="button" class="btn btn-success"
				onclick="NavigatePage('Booking')">Booking</button>
			<button type="button" class="btn btn-danger"
				onclick="NavigatePage('Approval')">Approval</button>
				<form method="post" action="<%=request.getContextPath()%>/pendingList">
				<button type="submit" >Test</button>
				<button type="submit" formaction="<%=request.getContextPath()%>/report">Test Report</button>
				</form>
			<button type="button" class="btn btn-warning"
				onclick="NavigatePage('Report')">Report</button>
		</div>
	</div>
</body>
</html>