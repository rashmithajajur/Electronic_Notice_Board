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
<script>

function choose() {
    if (document.getElementById('entity').value == 'studententity') {
        document.getElementById('student').style.display = 'block';
       } 
	else {
        document.getElementById('student').style.display = 'none';
    }
	
	if (document.getElementById('entity').value == 'professorentity') {
        document.getElementById('professor').style.display = 'block';
        
    } 
	
	else {
        document.getElementById('professor').style.display = 'none';
    }
	
	if (document.getElementById('entity').value == 'evententity') {
        document.getElementById('event').style.display = 'block';
        
    } 
	else {
        document.getElementById('event').style.display = 'none';
    }
	
	if (document.getElementById('entity').value == 'categoryentity') {
        document.getElementById('category').style.display = 'block';
        
    } 
	else {
        document.getElementById('category').style.display = 'none';
    }
	
	
	if (document.getElementById('entity').value == 'departmententity') {
        document.getElementById('department').style.display = 'block';
        
    } 
	else {
        document.getElementById('department').style.display = 'none';
    }
}

function validate()
{
	if(document.getElementById('entity').value=='studententity')
		{
	if( document.myForm.studentid.value == "" ||
           isNaN( document.myForm.studentid.value ) ||
           document.myForm.studentid.value.length != 10 )
   {
     alert( "Please provide 10 digit Student ID" );
     document.myForm.studentid.focus() ;
     return false;
   }
	
	 if( document.myForm.studentname.value == "" )
	   {
	     alert( "Please provide Student name!" );
	     document.myForm.studentname.focus() ;
	     return false;
	   }
	 
	 if( document.myForm.departmentName.value == "-1" )
	   {
	     alert( "Please provide deparrtment name!" );
	     return false;
	   }
	 
	 if( document.myForm.major.value == "" )
	   {
	     alert( "Please provide Student Major!" );
	     document.myForm.major.focus() ;
	     return false;
	   }
		}
	else if(document.getElementById('entity').value=='professorentity')
		{
	if( document.myForm.professorid.value == "" ||
	           isNaN( document.myForm.professorid.value ) ||
	           document.myForm.professortid.value.length != 10 )
	   {
	     alert( "Please provide 10 digit Professor ID" );
	     document.myForm.professorid.focus() ;
	     return false;
	   }
	
	if( document.myForm.professorname.value == "" )
	   {
	     alert( "Please provide Professor name!" );
	     document.myForm.professorname.focus() ;
	     return false;
	   }
		}
	
	else if(document.getElementById('entity').value=='evententity')
		{
	 if( document.myForm.eventname.value == "" )
	   {
	     alert( "Please provide Event name!" );
	     document.myForm.eventname.focus() ;
	     return false;
	   }
	 
	 if( document.myForm.eventdescription.value == "" )
	   {
	     alert( "Please provide Event Description!" );
	     document.myForm.eventdescription.focus() ;
	     return false;
	   }
	 
	 if( document.myForm.eventvenue.value == "" )
	   {
	     alert( "Please provide Event Venue!" );
	     document.myForm.eventvenue.focus() ;
	     return false;
	   }
	 if( document.myForm.eventdate.value == "" )
	   {
	     alert( "Please provide Event Date!" );
	     document.myForm.eventdate.focus() ;
	     return false;
	   }
	 if( document.myForm.totalseats.value == "" )
	   {
	     alert( "Please provide Total seats!" );
	     document.myForm.totalseats.focus() ;
	     return false;
	   }
	 if( document.myForm.totalprice.value == "" )
	   {
	     alert( "Please provide Total price!" );
	     document.myForm.totalprice.focus() ;
	     return false;
	   }
		}
	else if(document.getElementById('entity').value=='categoryentity')
		{
	 if( document.myForm.categoryname.value == "" )
	   {
	     alert( "Please provide categoryname!" );
	     document.myForm.categoryname.focus() ;
	     return false;
	   }
	 if( document.myForm.categorydescription.value == "" )
	   {
	     alert( "Please provide category description!" );
	     document.myForm.categorydescription.focus() ;
	     return false;
	   }
		}
	
	else if(document.getElementById('entity').value=='departmententity')
		{
	 if( document.myForm.departmentname.value == "" )
	   {
	     alert( "Please provide department name!" );
	     document.myForm.departmentname.focus() ;
	     return false;
	   }
	 
	 if( document.myForm.deptdescription.value == "" )
	   {
	     alert( "Please provide department description!" );
	     document.myForm.deptdescription.focus() ;
	     return false;
	   }
		}
	else
		{
		 alert( "proceedd!!!!!" );
		}
    return( true );
}
</script>


</head>

<body>
	<%
		PersonBean bean = (PersonBean) session.getAttribute("person");
		String username=null;
		if(bean!=null)
				username = bean.getFname();
		List<String> categories=(ArrayList<String>)session.getAttribute("categories");
		List<String> department=(ArrayList<String>)session.getAttribute("department");
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
					<form id="login" action="entityServlet" method="post" name="myForm"  onsubmit="return(validate());">
						<div class="templatemo_post_top">
							<h1>Add Entity: This Section allows the admin to add
								Category, department, Student or Professor</h1>
						</div>
						<div class="templatemo_post_mid" style="height: 400px">
							<table border="0">
								<tbody>
									<tr>
										<td><label for="entity">Entity: </label></td>
										<td><select id="entity" name="entity" style="width: 212px;" onclick='choose()' >
										<option id="1" value="selectentity">Select Any Entity</option>
										<option id="2" value="studententity">Student</option>
										<option id="3" value="professorentity">Professor</option>
										<option id="4" value="evententity">Events</option>
										<option id="5" value="categoryentity">Category</option>
										<option id="6" value="departmententity">Department</option>
										</select></td>
									</tr>
								</tbody>
							</table>
							<div id="student">
								<table border="0">
									<tbody>
										<tr>
											<td><label for="studentid">SJSU ID: </label><span class="red">*</span></td>
											<td><input id="studentid" maxlength="10"
												name="studentid" type="text" /></td>
									        <td id="studentid" class="red">&nbsp;</td>
										</tr>
											
										<tr>
											<td><label for="studentname">Student Name: </label></td>
											<td><input id="studentname" maxlength="100"
												name="studentname" type="text" /></td>
										</tr>
										<tr>
											<td><label for="department">Department :</label></td>
											<td><select name="departmentName">
											 <option value="-1" selected>Select</option>
											<%if(department!=null && department.size()!=0){ %>
													<% for(int i=0;i<department.size();i++) { %>
														<option value="<%=department.get(i)%>"><%=department.get(i)%></option>
													<% } %>
													<%}else{ %>
														<option value="Select">Select</option>
													<%} %>
											</select></td>
											</tr>
											<tr>
												<td><label for="major">Major: </label></td>
												<td><input id="major" maxlength="100"
													name="major" type="text" /></td>
											</tr>
									</tbody>
								</table>
							</div>
							<div id="professor">
								<table border="0">
									</tbody>
									<tr>
										<td><label for="professorid">Professor Id: </label></td><span class="red">*</span></td>
										<td><input id="professorid" maxlength="10"
											name="professorid" type="text" /></td>
											<td id="professoridid" class="red">&nbsp;</td>
									</tr>
									<tr>
										<td><label for="professorname">Professor Name : </label></td>
										<td><input id="professorname" maxlength="100"
											name="professorname" type="text" /></td>
									</tr>
									<tr>
										
										<td><label for="department">Department :</label></td>
										<td><select name="department">
										<%if(department!=null && department.size()!=0){ %>
												<% for(int i=0;i<department.size();i++) { %>
													<option value="<%=department.get(i)%>"><%=department.get(i)%></option>
												<% } %>
												<%}else{ %>
													<option value="Select">Select</option>
												<%} %>
										</select></td>
									</tr>
									<tr>
										<td><label for="subjects">Subjects : </label></td>
										<td><input id="subjects" maxlength="500"
											name="subjects" type="text" /></td>
									</tr>
									</tbody>
								</table>
							</div>
							<div id="event">
								<table border="0">
									</tbody>
									<tr>
										<td><label for="category">Category: </label></td>
										<td><select name="category">
												<%if(categories!=null && categories.size()!=0){ %>
												<% for(int i=0;i<categories.size();i++) { %>
													<option value="<%=categories.get(i)%>"><%=categories.get(i)%></option>
												<% } %>
												<%}else{ %>
													<option value="Select">Select</option>
												<%} %>
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
										<td><input type="text" id="eventdate" name="eventdate" maxlength="19"></td>
									</tr>

									<tr>
										<td><label for="totalseats">Total Seats : </label></td>
										<td><input id="totalseats" maxlength="5"
											name="totalseats" type="text" /></td>
									</tr>

									<tr>
										<td><label for="ticketprice">Ticket Price: </label></td>
										<td><input id="ticketprice" maxlength="10"
											name="ticketprice" type="text" /></td>
									</tr>
									</tbody>
								</table>
							</div>
							<div id="category">
								<table border="0">
									<tbody>
										<tr>
											<td><label for="categoryname">Category Name: </label></td>
											<td><input id="categoryname" maxlength="50"
												name="categoryname" type="text" /></td>
										</tr>

										<tr>
											<td><label for="description">Category
													Description: </label></td>
											<td><input id="categorydescription" maxlength="100"
												name="categorydescription" type="text" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div id="department" >
								<table border="0">
									<tbody>
										<tr>
											<td><label for="departmentname">Department Name:
											</label></td>
											<td><input id="departmentname" maxlength="50"
												name="departmentname" type="text" /></td>
										</tr>

										<tr>
											<td><label for="deptdescription">Department
													Description: </label></td>
											<td><input id="deptdescription" maxlength="100"
												name="deptdescription" type="text" /></td>
										</tr>
										<tr>
											<td colspan="5"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<table><tbody><tr><td>
							<input type="submit" value="Submit"></td></tr></tbody></table>
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