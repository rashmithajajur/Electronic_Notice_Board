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
			StudentBean bean = (StudentBean)session.getAttribute("person");
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
						<a href="studentindex.jsp">Electronic Student Notice Board</a>
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
						<h1>Update Information</h1>
					</div>
					<div class="templatemo_post_mid" style="height:400px">
						<form id="updatestudent" action="studentServlet" method="post">
							<table border="0">
										</tbody>
										
										<tr>
											<td><label for="sjsuid">SJSU Id :</label></td>
											<td><input id="userid" maxlength="50" name="userid" value=<%=bean.getUserid() %>
												type="text" disabled="disabled" /></td>
										</tr>
	
										<tr>
											<td><label for="studentfirstname">Student First Name </label> </td>
											<td><input id="studentfirstname" maxlength="100" name="studentfirstname" type="text" value=<%=bean.getFname() %> ></input> </td>
											</tr>
											
											<tr>
											<td><label for="studentlastname">Student Last Name </label> </td>
											<td><input id="studentlasttname" maxlength="100" name="studentlastname" type="text"  value=<%=lname%> ></input> </td>
											</tr>
											
											<tr>
												<td><label for="address">Address</label> </td>
												<td><textarea rows="4" cols="20" id="address" name="address"><%=address %> </textarea> </td>
											</tr>
											
											<tr>
												<td><label for="department">Department</label> </td>
												<td><input id="department" maxlength="100" name="department" type="text" value=<%=bean.getDepartmentName() %> disabled="disabled" /> </td>
											</tr>
															
											<tr>
												<td><label for="major">Major</label> </td>
												<td><input id="major" maxlength="75" name="major" type="text" disabled="disabled" value=<%=bean.getMajor() %> /> </td>
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