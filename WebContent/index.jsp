<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaniaDS Log-in</title>
<link href="css/index.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="IndexLogo"></div>
	<div class="login-block">
	    <h1>Login</h1>
	    <form class = "loginForm" action="loginServlet" method="post">
		    <input type="text" value="" placeholder="Username" name="username" />
		    <input type="password" value="" placeholder="Password" name="password" />
		    <button>Submit</button>
	    </form>
	</div>
</body>
</html>
