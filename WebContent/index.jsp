<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta charset="utf-8">

	<title>Login</title>
	<link href="styleLogin.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Varela+Round">
	<script>
	function fixmemberId(txtBox) {
       if (txtBox == null) { 
           return '' }
       
       if (txtBox.value.length == 9) {
           txtBox.value = txtBox.value.substring(0, 3) + '-' + txtBox.value.substring(3, 5) + '-' + txtBox.value.substring(5, 9)
       }
       
       return txtBox.value;
   	}
   	function validateLogin(login)   
   {
	 var pattern = /^\d{3}-\d{2}-\d{4}$/;
     if(login.sjsuid.value==null ||login.sjsuid.value == ''){
			alert('SJSU ID cannot be empty');
			return false;
	 }else if(!login.sjsuid.value.match(pattern)){
	 	alert('SJSU ID is in invalid format. It should be all digits');
		return false;
	 }
	if(login.password.value==null ||login.password.value == ''){
		alert('Password cannot be empty');
		return false;
	}
		login.sjsuid.value=login.sjsuid.value.replace('-','');
		login.sjsuid.value=login.sjsuid.value.replace('-','');
		return true;
	}
	</script>
</head>

<body>

	<div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>

		<form action="loginServlet" method="POST">

			<fieldset>

				<div style="color:red; font-weight:bold;" align="center">
				</div>
				<br/>
				
				<p><label for="id">SJSU Id:</label></p>
				<p><input type="memNo" name = "sjsuid" id="sjsuid"></p>

				<p><label for="password">Password</label></p>
				<p><input type="password" name ="password" id="password"></p>

				<p><input type="submit" value="Sign In"></p>
				<p>New Student or Faculty? Click Here proceed registration <a href="register.jsp">here</a></p>
			</fieldset>

		</form>

	</div> <!-- end login -->

</body>
</html>