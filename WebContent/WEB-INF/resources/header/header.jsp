<%@page import="bean.user"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome to Someone's Bank</title>

<%
	user User = (user) session.getAttribute("User");
%>
<div class="home">
	<a href="main.jsp">Home button</a>

	<div class="loggedIn">
		<b>Hey, <%=User.getFullname()%>.Your status is : <%=User.getRole()%></b>
	</div>
</div>
<div class="logout">
	<form name="logoutForm" action="logoutServlet" method="post">
		<button>Logout</button>
	</form>
</div>
<style>
header {
	color: white;
	background-color: #655fe8;
	overflow: hidden;
	padding: 3%;
}

header .home {
	width: 20%;
}

header div {
	display: inline;
}

header .loggedIn {
	display: inline;
	clear: left;
	width: 50%;
	text-align: center;
}

header .logout {
	float: right;
	width: 20%;
}

header button {
	width: 100px;
	background: #7c8c7d;
	box-sizing: border-box;
	border-radius: 5px;
	border: 1px solid #7c8c7d;
	color: #fff;
	font-weight: bold;
	text-transform: uppercase;
	font-size: 12px;
	font-family: Montserrat;
	outline: none;
	cursor: pointer;
	height: 44px;
}

header button:hover {
	background: #2c6bd6;
}

header input {
	text-align: center;
	box-sizing: border-box;
	border-radius: 5px;
	border: 1px solid #ccc;
	margin-bottom: 2px;
	font-size: 14px;
	font-family: Montserrat;
	outline: none;
}

header a:link, a:visited {
	background-color: #7c8c7d;
	color: white;
	padding: 14px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	border-radius: 5px;
}

a:hover, a:active {
	background-color: #2c6bd6;
}
</style>
</header>
</body>
</html>