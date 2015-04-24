<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.beans.PersonBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>SJSU Electronic Notice Board</title>

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<%
		PersonBean bean = (PersonBean) session.getAttribute("person");
		String username = null;
		if (bean != null)
			username = bean.getFname();
		List<String> department = (ArrayList<String>) session
				.getAttribute("department");
	%>
	<div id="templatemo_background_section_top">

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
						<li><a href="viewpasteventsprofessor.jsp">View All Events</a></li>
						<li><a href="updateprofessorinfo.jsp">Update Info</a></li>
						<li><a href="addeventsprofessor.jsp">Add Events</a></li>
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
					<form id="login" action="entityServlet" method="post" name="myForm"
						onsubmit="return(validate());">
						<div class="templatemo_post_top">
							<h1>Add Events: This Section allows the Professor to Add
								Events Departmentwise</h1>
						</div>
						<input type="hidden" value="evententity" name="entity" id="entity"/>
						<input type="hidden" value="Y" name="fromprof" id="fromprof"/>
						<div class="templatemo_post_mid" style="height: 400px">

							<table border="0">
								</tbody>
								<tr>
									<td><label for="department">Department: </label></td>
									<td><select name="department">
											<%
												if (department != null && department.size() != 0) {
											%>
											<%
												for (int i = 0; i < department.size(); i++) {
											%>
											<option value="<%=department.get(i)%>"><%=department.get(i)%></option>
											<%
												}
											%>
											<%
												} else {
											%>
											<option value="Select">Select</option>
											<%
												}
											%>
									</select></td>
								</tr>
								<tr>
									<td><label for="eventname">Event Name :</label></td>
									<td><input id="eventname" maxlength="50" name="eventname"
										type="text" /></td>
								</tr>

								<tr>
									<td><label for="eventdescription">Event
											Description :</label></td>
									<td><input id="eventdescription" maxlength="1000"
										name="eventdescription" type="text" /></td>
								</tr>

								<tr>
									<td><label for="eventvenue">Event Venue:</label></td>
									<td><input type="text" id="venue" name="venue"
										maxlength="150"></td>
								</tr>

								<tr>
									<td><label for="eventdate">Event Date :</label></td>
									<td><input type="text" id="eventdate" name="eventdate"
										maxlength="19"></td>
								</tr>

								<tr>
									<td><label for="totalseats">Total Seats : </label></td>
									<td><input id="totalseats" maxlength="5" name="totalseats"
										type="text" /></td>
								</tr>

								<tr>
									<td><label for="ticketprice">Ticket Price: </label></td>
									<td><input id="ticketprice" maxlength="10"
										name="ticketprice" type="text" /></td>
								</tr>
								<tr>
									<td><input type="submit" value="Submit"></td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="templatemo_post_bottom">
							<span class="post">Developed By: SJSU Students CMPE180-38
								batch</span> <span class="post">Category: <a href="#">Java</a></span>
						</div>
					</form>
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