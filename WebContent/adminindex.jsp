<%@page import="com.beans.PersonBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


</style>
<title>SJSU Electronic Notice Board</title>

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="templatemo_background_section_top">
    	<% PersonBean bean = (PersonBean)session.getAttribute("person"); 
			String username = bean.getFname();%>
    	<div class="templatemo_container">
		
        	<div id="templatemo_header">
				<div id="templatemo_logo_section">            
		        	<h1><a href="studentindex.jsp">Electronic Student Notice Board</a></h1>            
					<h2>Find out happening events in the campus.</h2>
				</div>
				<div id="templatemo_user_section">            
		        	Welcome <%=username	 %>!!<a href="index.jsp">Logout</a>
				</div>
         	</div><!-- end of headder -->
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
           </div> <!-- end of menu -->
		</div><!-- end of container-->
        
	</div><!-- end of templatemo_background_section_top-->
    
    <div id="templatemo_background_section_middle">
    
    	<div class="templatemo_container">
        
        	<div id="templatemo_left_section">
            	
                <div class="templatemo_post">
                
                	<div class="templatemo_post_top">
                    	<h1>Welcome to the Electronic Student Notice Board</h1>
                    </div>
                    <div class="templatemo_post_mid" style="height:400px">
                    	  
						<p>Welcome Administrator!!</p>
						<br/>
						<p>You can perform following functions on the application</p>
						<p>There are various entities like Department, Category, Student, Professor and events in the complete system.</p>
						<p>You can add/delete the above mentioned entities. </p>						                      
                        <p>You can also view the summary of various entities. For example, summary of student will tell you which students are registered and which are not registered in the system. </p>
                        <p>Summary of events will gives summary of specific events saying number of seats booked and total amount collected after bookings</p>
                        <div class="clear"></div>
                        
                    </div>
                    <div class="templatemo_post_bottom">
						
                    	<span class="post">Developed By: SJSU Students CMPE180-38 batch</span>
                        <span class="post">Category: <a href="#">java</a></span>
                    </div>
                    
				</div><!-- end of templatemo_post-->
                
            </div><!-- end of left section-->
            
            
        </div><!-- end of container-->
	</div><!-- end of background middle-->
    
     
<div align=center>Created by SJSU Fall 2014 students for CMPE 180-38 Class Project</div></body>


</html>