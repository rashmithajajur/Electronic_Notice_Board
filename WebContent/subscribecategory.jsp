<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.beans.StudentBean"%>
<%@page import="com.beans.EventsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.beans.PersonBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%
		PersonBean bean = (PersonBean) session.getAttribute("person");
		String username=null;
		if(bean!=null)
				username = bean.getFname();
		List<String> categories=(ArrayList<String>)session.getAttribute("categories");
		List<String> department=(ArrayList<String>)session.getAttribute("department");
		HashMap<String,Boolean> categoryMap=new HashMap<String, Boolean>();
		categoryMap=(HashMap<String, Boolean>)session.getAttribute("categoryMap");
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
						<h1>Subscribe Category</h1>
					</div>
					<div class="templatemo_post_mid" style="height: 400px">
						<form id="subscribecategory" action="categoryServlet"
							method="post">
							<table border="0">
								</tbody>
								<c:forEach items="${categoryMap}" var="cat">
									<tr>
										<c:choose>
											<c:when test="${cat.value}">
												<td style="padding-top: 5px"><input type="checkbox" name="category"
													value="${cat.key}" checked="checked" /></td>
												<td><b>${cat.key}</b></td>
											</c:when>
											<c:otherwise>
												<td style="padding-top: 5px"><input type="checkbox" name="category"
													value="${cat.key}" /></td>
												<td>${cat.key}</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
								<tr></tr>
								<tr></tr>
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