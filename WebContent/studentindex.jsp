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
			ArrayList<EventsBean> events = (ArrayList<EventsBean>) session
					.getAttribute("upcomingevents");
			int alertCount = 0;
			if(session.getAttribute("alertCount")!=null){
				alertCount = (Integer)session.getAttribute("alertCount");
			}
			
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
			<!-- end of headder -->
			<div id="templatemo_menu_panel">
				<div id="templatemo_menu_section">

					<ul>
						<li><a href="viewpastevents.jsp">View All Events </a></li>
						<li><a href="updatestudentinfo.jsp">Update Info </a></li>
						<li><a href="booktickets.jsp">Book Tickets</a></li>
						<li><a href="subscribecategory.jsp">Subscribe Category</a></li>
						<li><a href="changepassword.jsp">Change Password</a></li>
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
					<p>Welcome Student Name. You have <a href="studentalerts.jsp"><%=alertCount %></a> alert(s)!!</p>
					
						<br /> 
							<h2 align="center" style="color:white"> Upcoming Events</h2>
						<br />
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
								if (events != null && events.size() > 0) {

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
								}
								}
							%>
						</table>

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

</body>


</html>