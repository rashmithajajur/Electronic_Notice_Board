<%@page import="com.servlet.EventSummary"%>
<%@page import="com.beans.EventSearchBean"%>
<%@page import="com.beans.EventsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.beans.PersonBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


</style>
<title>SJSU Electronic Notice Board</title>

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="templatemo_background_section_top">
		<%
			PersonBean bean = (PersonBean) session.getAttribute("person");
			String username = bean.getFname();
			ArrayList<EventsBean> events = null;
			EventSummary eventSummary = (EventSummary)session.getAttribute("eventSummary");
			events=eventSummary.getEventBeanList();
		%>
		<div class="templatemo_container">

			<div id="templatemo_header">
				<div id="templatemo_logo_section">
					<h1>
						<a href="studentindex.jsp">Electronic Student Notice Board</a>
					</h1>
					<h2>Find out happening events in the campus.</h2>
				</div>
				<div id="templatemo_user_section">
					Welcome
					<%=username%>!!<a href="index.jsp">Logout</a>
				</div>
			</div>
			<!-- end of header -->
			<div id="templatemo_menu_panel">
				<div id="templatemo_menu_section">
					<ul>
					  <li><a href="addentity.jsp">Add Entity</a></li>
					  <li><a href="deleteentity.jsp">Delete Entity</a></li>
					  <li><a href="eventsummary.jsp">Events Summary</a></li>
					  <li><a href="studentsummary.jsp">Student Summary</a></li>
					  <li><a href="professorsummary.jsp">Professor Summary</a></li>		
					</ul>
				</div>
			</div>
			<!-- end of menu -->
		</div>
		<!-- end of container-->

	</div>
	<!-- end of templatemo_background_section_top-->

	<div id="templatemo_background_section_middle">

		<div class="templatemo_container">

			<div id="templatemo_left_section">

				<div class="templatemo_post">

					<div class="templatemo_post_top">
						<h1>Welcome to the Electronic Student Notice Board</h1>
					</div>
					<div class="templatemo_post_mid" style="height: 600px">
						<h2 align="center" style="color: white">Summarizing events from current Date till end of third month</h2>
						<br />
						<%
							if (events != null && events.size() > 0) {
						%>
						<table class="listData" border=1>
							<tr>

								<th style="color: white;">Event Name</th>
								<th style="color: white;">Description</th>
								<th style="color: white;">Event Date</th>
								<th style="color: white;">Venue</th>
								<th style="color: white;">Total Tickets</th>
								<th style="color: white;">Available Tickets</th>
								<th style="color: white;">Ticket Price</th>
							</tr>


							<%
								for (int i = 0; i < events.size(); i++) {
										EventsBean event = events.get(i);
							%>
							<tr>
								<td><%=event.getEventName()%></td>
								<td><%=event.getDescription()%></td>
								<td><%=event.getEventDate()%></td>
								<td><%=event.getVenue()%></td>
								<td><%=event.getTotalNoOfSeats()%></td>
								<td><%=event.getTotalNoOfSeats()
							- event.getBookedSeats()%></td>
								<td><%=event.getTicketPrice()%></td>
							</tr>
							
							<%
								}%>
							</table>	
							<%
								} else {
							%>
							<p>No records found</p>
							<%
								}
							%>
							<br/>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;">Total Tickets Booked:: </label>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;"><b><%=eventSummary.getTotalTicketsSold() %></b></label>
							<br/>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;">Total Revenue Generated:: </label>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;"><b><%=eventSummary.getTotalRevenue() %></b></label>
							<br/>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;">Total Available Bookings:: </label>
							<label style="color: #fff;margin-left: 15px;font-size: 15px;"><b><%=eventSummary.getTotalSeats()-eventSummary.getTotalTicketsSold() %></b></label>
						<div class="clear"></div>

					</div>
					<div class="templatemo_post_bottom">

						<span class="post">Developed By: SJSU Students CMPE180-38
							batch</span> <span class="post">Category: <a href="#">java</a></span>
					</div>

				</div>
				<!-- end of templatemo_post-->

			</div>
			<!-- end of left section-->


		</div>
		<!-- end of container-->
	</div>
	<!-- end of background middle-->


	<div align=center>Created by SJSU Fall 2014 students for CMPE
		180-38 Class Project</div>
</body>


</html>