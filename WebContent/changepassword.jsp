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

<script type="text/javascript">

  
	function validatePassword()   
   {
	
		 if(document.changepwd.oldpwd.value==null ||document.changepwd.oldpwd.value == ''){
				alert('Old Password cannot be Empty');
				return false;
		 }
		 if(document.changepwd.newpwd1.value==null ||document.changepwd.newpwd1.value == ''){
				alert('New Password cannot be blank');
				return false;
		} 
		if(document.changepwd.newpwd2.value==null ||document.changepwd.newpwd2.value == ''){
				alert('Please Re-enter new Password');
				return false;
		} 
		if(document.changepwd.newpwd1.value!=document.changepwd.newpwd2.value){
				alert('New entered and re-entered password should be equal');
				return false;
		} 
    }
</script>
<title>SJSU Electronic Notice Board</title>

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="templatemo_background_section_top">
		<% PersonBean bean = (PersonBean)session.getAttribute("person"); 
			String username = bean.getFname();
			String pageFwded="";
			if(bean.getType().equalsIgnoreCase("STUDENT")){
				pageFwded="studentindex.jsp";
			}else{
				pageFwded="professorindex.jsp";
			}
		%>
		<div class="templatemo_container">

			<div id="templatemo_header">
				<div id="templatemo_logo_section">
					<h1>
						<a href=<%=pageFwded%>>Electronic Student Notice Board</a>
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

					<%if(bean.getType().equalsIgnoreCase("STUDENT")){ %>
					<ul>
						<li><a href="viewpastevents.jsp">View All Events </a></li>
						<li><a href="updatestudentinfo.jsp">Update Info </a></li>
						<li><a href="booktickets.jsp">Book Tickets</a></li>
						<li><a href="subscribecategory.jsp">Subscribe Category</a></li>
						<li><a href="changepassword.jsp">Change Password</a></li>
					</ul>
					<%}else{%>
						<ul>
						  <li><a href="viewpasteventsprofessor.jsp">View All Events</a></li>
						  <li><a href="updateprofessorinfo.jsp">Update Info</a></li>
						  <li><a href="addeventsprofessor.jsp">Add Events</a></li>
						  <li><a href="changepassword.jsp">Change Password</a></li>			
						</ul> 
					<%}%>

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
						<h1>Change Password</h1>
					</div>
					<div class="templatemo_post_mid" style="height:400px">

						<form name="changepwd" onsubmit="return validatePassword()" action="changepwdServlet" method="post">                    
                        <table><tbody>                        
							<tr>
								<td><label for="oldpwd">Old Password*: </label> </td>
								<td><input id="oldpwd" maxlength="75" name="oldpwd" type="password"/></td>
							</tr>
							<tr>
								<td><label for="newpwd1">New Password*: </label> </td>
								<td><input id="newpwd1" maxlength="75" name="newpwd1" type="password"/></td>
							</tr>
							<tr>
								<td><label for="newpwd2">Re-enter New Password*: </label> </td>
								<td><input id="newpwd2" maxlength="75" name="newpwd2" type="password"/></td>
							</tr>
						</tbody></table>
						<% String message = (String)session.getAttribute("message"); 
						if(session.getAttribute("message") != null)
						{ %>
							<p><%=message %></p>
						<%} %>
						<br/>
						<div><input name="Submit" type="submit" value="Submit" style="width:70px; align:right; margin-left:80px"/>
						<input type="button" value="Reset" style="width:70px; align:right;"/></div>
						</form>

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