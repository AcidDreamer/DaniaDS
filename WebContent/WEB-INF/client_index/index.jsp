<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaniaDS Log-in</title>
<link href="index/index.css" rel="stylesheet" type="text/css"/>
<!-- Deutero load tou css se periptwsei pou den fortwnete ws arxiki -->
<link href="index.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="IndexLogo"></div>
	<div class="login-block">
	    <h1>Login</h1>
	    <form action="<%=request.getContextPath()%>/index/index.jsp" id='login'>
		    <input type="text" value="" placeholder="Username" id="username" />
		    <input type="password" value="" placeholder="Password" id="password" />
		    <button>Submit</button>
	    </form>
	</div>
</body>
</html>
