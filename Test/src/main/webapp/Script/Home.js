function NavigatePage(Page) {
	var URL = "Booking.jsp";
	if (Page == "Approval") {
		URL = "BookingApproval.jsp";
	} else if (Page == "Report") {
		URL = "BookingReport.jsp";
	}
	window.location.href = URL;
}