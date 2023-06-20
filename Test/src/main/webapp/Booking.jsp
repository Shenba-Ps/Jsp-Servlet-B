<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Booking</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Plugins/Boostrap.css">
<link rel="stylesheet" href="Script/Common.css">
<script src="Plugins/jQuery.js"></script>
<script src="Plugins/bootstrap.js"></script>
<script src="Script/Booking.js"></script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/saveBooking" method="post">
		<div style="background-color: #4169E1;">
			<div>
				<input type="hidden" id="hdnSelectedSlots" name="nmSelectedSlots"
					value=""> <input type="hidden" id="hdnTotalAmount"
					name="nmTotalAmount" value=""> <input type="hidden"
					id="hdnAction" name="nmAction"
					value="<%=request.getAttribute("hdnAction")%>"><input
					type="hidden" id="hdnUserBookingCountForMonth"
					name="hdnUserBookingCountForMonth"
					value="<%=request.getAttribute("atrUserBookingCountForMonth")%>"><input
					type="hidden" id="hdnUserBookingForDate"
					name="hdnUserBookingForDate"
					value="<%=request.getAttribute("atrUserBookingForDate")%>"><input
					type="hidden" id="hdnSelectedSlotByDate"
					name="hdnSelectedSlotByDate"
					value="<%=request.getAttribute("atrSelectedSlotByDate")%>">
				<div class="Level form-group">
					<div class="form-group" onClick="OpenCustomerDetail()"
						style="font-style: underline; color: blue;">Edit Customer
						Detail</div>
					<div class="Title form-group" style="text-align: center;">LEVEL
						1</div>
					<div class="row">
						<div class="col-md-2 Slot Free" id="divL1S1"
							onclick="onclickSlotc('L1S1')">S1</div>
						<div class="col-md-2 Slot Free" id="divL1S2"
							onclick="onclickSlotc('L1S2')">S2</div>
						<div class="col-md-2 Slot Free" id="divL1S3"
							onclick="onclickSlotc('L1S3')">S3</div>
						<div class="col-md-2 Slot Free" id="divL1S4"
							onclick="onclickSlotc('L1S4')">S4</div>
						<div class="col-md-2 Slot Free" id="divL1S5"
							onclick="onclickSlotc('L1S5')">S5</div>
						<div class="col-md-2 Slot Free" id="divL1S6"
							onclick="onclickSlotc('L1S6')">S6</div>
						<div class="col-md-2 Slot Free" id="divL1S7"
							onclick="onclickSlotc('L1S7')">S7</div>
						<div class="col-md-2 Slot Free" id="divL1S8"
							onclick="onclickSlotc('L1S8')">S8</div>
						<div class="col-md-2 Slot Free" id="divL1S9"
							onclick="onclickSlotc('L1S9')">S9</div>
						<div class="col-md-2 Slot Free" id="divL1S10"
							onclick="onclickSlotc('L1S10')">S10</div>
						<div class="col-md-2 Slot Free" id="divL1S11"
							onclick="onclickSlotc('L1S11')">S11</div>
						<div class="col-md-2 Slot Free" id="divL1S12"
							onclick="onclickSlotc('L1S12')">S12</div>
						<div class="col-md-2 Slot Free" id="divL1S13"
							onclick="onclickSlotc('L1S13')">S13</div>
						<div class="col-md-2 Slot Free" id="divL1S14"
							onclick="onclickSlotc('L1S14')">S14</div>
						<div class="col-md-2 Slot Free" id="divL1S15"
							onclick="onclickSlotc('L1S15')">S15</div>
						<div class="col-md-2 Slot Free" id="divL1S16"
							onclick="onclickSlotc('L1S16')">S16</div>
						<div class="col-md-2 Slot Free" id="divL1S17"
							onclick="onclickSlotc('L1S17')">S17</div>
						<div class="col-md-2 Slot Free" id="divL1S18"
							onclick="onclickSlotc('L1S18')">S18</div>
						<div class="col-md-2 Slot Free" id="divL1S19"
							onclick="onclickSlotc('L1S19')">S19</div>
						<div class="col-md-2 Slot Free" id="divL1S20"
							onclick="onclickSlotc('L1S20')">S20</div>
					</div>
				</div>
				<div class="Level form-group">
					<div class="Title form-group" style="text-align: center;">LEVEL
						2</div>
					<div class="row">
						<div class="col-md-2 Slot Free" id="divL2S1"
							onclick="onclickSlotc('L2S1')">S1</div>
						<div class="col-md-2 Slot Free" id="divL2S2"
							onclick="onclickSlotc('L2S2')">S2</div>
						<div class="col-md-2 Slot Free" id="divL2S3"
							onclick="onclickSlotc('L2S3')">S3</div>
						<div class="col-md-2 Slot Free" id="divL2S4"
							onclick="onclickSlotc('L2S4')">S4</div>
						<div class="col-md-2 Slot Free" id="divL2S5"
							onclick="onclickSlotc('L2S5')">S5</div>
						<div class="col-md-2 Slot Free" id="divL2S6"
							onclick="onclickSlotc('L2S6')">S6</div>
						<div class="col-md-2 Slot Free" id="divL2S7"
							onclick="onclickSlotc('L2S7')">S7</div>
						<div class="col-md-2 Slot Free" id="divL2S8"
							onclick="onclickSlotc('L2S8')">S8</div>
						<div class="col-md-2 Slot Free" id="divL2S9"
							onclick="onclickSlotc('L2S9')">S9</div>
						<div class="col-md-2 Slot Free" id="divL2S10"
							onclick="onclickSlotc('L2S10')">S10</div>
					</div>
				</div>
				<div class="row form-group" style="padding: 50px;">
					<div class="col-md-12 form-group">
						<b>Total amount payable:</b>
						<div class="Title" id="divTotalPrice">$ 0.00</div>
					</div>
					<div class="col-md-12 form-group">
						<input id="chkAcknowledge" type="checkbox"> I acknowledge
						to pay the sum of stated amount upon approval</input>
					</div>
					<div class="col-md-12 form-group">
						<button type="button" class="btn btn-warning"
							onclick="onclickSubmit()">Submit</button>
						<button type="submit" class="btn btn-warning" id="btnSubmit"
							formaction="<%=request.getContextPath()%>/saveBooking"
							style="display: none;"></button>
					</div>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" data-backdrop="static" id="mdlCustomerDetail"
				role="dialog">
				<div class="modal-dialog" style="width: 70%;">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-body">
							<div class="Title form-group" style="text-align: center;">Please
								enter your booking information</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Name</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control InputClear" type="text" id="idName"
										name="nmName" value="">
									<div class="ControlErrorMessage" id="errName"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Address</label>
								</div>
								<div class="col-xs-6">
									<textarea class="form-control InputClear" id="idAddress"
										name="nmAddress"></textarea>
									<div class="ControlErrorMessage" id="errAddress"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>NRIC</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control InputClear" type="text" id="idNRIC"
										name="nmNRIC" value="" maxlength="9">
									<div class="ControlErrorMessage" id="errNRIC"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Usage Trade</label>
								</div>
								<div class="col-xs-6">
									<select class="form-control" name="UsageTrade"
										id="idUsageTrade" value="">
										<option value="Credit Card Promotion">Credit Card
											Promotion</option>
										<option value="FB">F&B</option>
										<option value="Hairdressing">Hairdressing</option>
										<option value="Clothing">Clothing</option>
										<option value="Others">Others</option>
									</select>
									<div class="ControlErrorMessage" id="errUserTrade"></div>
								</div>
							</div>
							<div class="row form-group Others" style="display: none;">
								<div class="col-xs-3 RightAlign">
									<label>Others</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control InputClear" type="text"
										id="idOthers" name="nmOthers"
										value="<%=request.getAttribute("atrOthers")%>">
									<div class="ControlErrorMessage" id="errOthers"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Email</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control InputClear" type="text" id="idEmail"
										name="nmEmail" value="">
									<div class="ControlErrorMessage" id="errEmail"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Home</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control LandlineNumber InputClear" type="text"
										id="idHomeTel" name="nmHome" maxlength="8" value="">
									<div class="ControlErrorMessage" id="errHomeTel"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Office</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control PhoneNumber InputClear" type="text"
										id="idOfficeTel" name="nmOffice" maxlength="8" value="">
									<div class="ControlErrorMessage" id="errOfficeTel"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Mobile</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control PhoneNumber InputClear" type="text"
										id="idMobile" name="nmMobile" maxlength="8" value="">
									<div class="ControlErrorMessage" id="errMobile"></div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-3 RightAlign">
									<label>Booking Date</label>
								</div>
								<div class="col-xs-6">
									<input class="form-control InputClear" type="date"
										id="idBookingDate" name="nmBookingDate" value="">
									<div class="ControlErrorMessage" id="errBookingDate"></div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								onclick="onclickBookSlot()">Book Slot</button>
							<button type="submit" class="btn btn-primary" id="btnBookingSlot"
								style="display: none;"
								formaction="<%=request.getContextPath()%>/list"></button>
							<div class="ControlErrorMessage" id="errBookingSlot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>