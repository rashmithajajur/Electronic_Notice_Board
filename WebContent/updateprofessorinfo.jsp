<%@page import="com.beans.ProfessorBean"%>
<%@page import="com.beans.StudentBean"%>
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
			ProfessorBean bean = (ProfessorBean)session.getAttribute("person");
			String fname = bean.getFname();
			String lname = "";
			String address = "";
			if(bean.getLname()!=null)
				lname = bean.getLname();
			if(bean.getAddress()!=null)
				address = bean.getAddress();
			
			String username = (String)session.getAttribute("username");
			
		%>
		<div class="templatemo_container">

			<div id="templatemo_header">
				<div id="templatemo_logo_section">
					<h1>
						<a href="professorindex.jsp">Electronic Student Notice Board</a>
					</h1>
					<h2>Find out happening events in the campus.</h2>
				</div>
				<div id="templatemo_user_section">
					Welcome
					<%=username %>!!<a href="index.jsp">Logout</a>
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

					<div class="templatemo_post_top">
						<h1>Update Information</h1>
					</div>
					<div class="templatemo_post_mid" style="height:400px">
						<form id="updateprofessor" action="professorServlet" method="post">
							<table border="0">
										</tbody>
										
										<tr>
											<td><label for="sjsuid">SJSU Id :</label></td>
											<td><input id="userid" maxlength="50" name="userid" value=<%=bean.getUserid() %>
												type="text" disabled="disabled" /></td>
										</tr>
	
										<tr>
											<td><label for="professorfname">Professor First Name </label> </td>
											<td><input id="professorfname" maxlength="100" name="professorfname" type="text" value=<%=bean.getFname() %> ></input> </td>
											</tr>
											
											<tr>
											<td><label for="professorlname">Student Last Name </label> </td>
											<td><input id="professorlname" maxlength="100" name="professorlname" type="text"  value=<%=lname%> ></input> </td>
											</tr>
											
											<tr>
												<td><label for="address">Address</label> </td>
												<td><textarea rows="4" cols="20" id="address" name="address"><%=address %></textarea> </td>
											</tr>
											
											<tr>
												<td><label for="department">Department</label> </td>
												<td><input id="department" maxlength="100" name="department" type="text" value=<%=bean.getDepartmentName() %> disabled="disabled" /> </td>
											</tr>
															
											<tr>
												<td><label for="subjects">Subjects</label> </td>
												<td><input id="subjects" maxlength="75" name="subjects" type="text" disabled="disabled" value=<%=bean.getSubjects() %> /> </td>
											</tr>
											<tr>
												<td><input type="submit" value="Submit"></td>
											</tr>
										</tbody>
									</table>
	
							
							<div class="clear"></div>
						</form>
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