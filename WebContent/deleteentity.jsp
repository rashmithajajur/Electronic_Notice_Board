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
		List<String> categories = (ArrayList<String>) session
				.getAttribute("categories");
		List<String> department = (ArrayList<String>) session
				.getAttribute("department");
	%>
	<div id="templatemo_background_section_top">

		<div class="templatemo_container">

			<div id="templatemo_header">
				<div id="templatemo_logo_section">
					<h1>
						<a href="adminindex.jsp">Electronic Student Notice Board</a>
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
					<form id="login" action="entityServlet" method="post" name="myForm">
						<div class="templatemo_post_top">
							<h1>Delete Entity: This Section allows the admin to Delete
								Event, Category, department, Student or Professor</h1>
						</div>
						<input type="hidden" id="isDelete" name="isDelete" value="Y"></input>
						<div class="templatemo_post_mid" style="height: 400px">
							<table border="0">
								<tbody>
									<tr>
										<td><label for="entity">Entity: </label></td>
										<td><select id="entity" name="entity"
											style="width: 212px;" onclick='choose()'>
												<option id="1" value="selectentity">Select Any
													Entity</option>
												<option id="2" value="studententity">Student</option>
												<option id="3" value="professorentity">Professor</option>
												<option id="4" value="evententity">Events</option>
												<option id="5" value="categoryentity">Category</option>
												<option id="6" value="departmententity">Department</option>
										</select></td>
									</tr>
									<tr>
										<td><label for="entityattr">Parameter for Selected Entity: </label></td>
										<td><input id="ID" maxlength="50"
											name="ID" type="text" /></td>
											<td style="font-size: 8px"><label>Incase of category enter full category name, Incase of department enter full department name, Incase of event enter full event name
											& Incase of student or professor enter full 10 digit SJSU Id</label></td> 
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