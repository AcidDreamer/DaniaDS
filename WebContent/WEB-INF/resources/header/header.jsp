<%@page import="bean.user"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/header.css" rel="stylesheet" type="text/css" />

<title>Welcome to Someone's Bank</title>

<%
	user User = (user) session.getAttribute("User");
%>
<div class="home">
	<a href="main.jsp">Home</a>

	<div class="loggedIn">
		<b>Hey, <%=User.getFullname()%>.Your status is : <%=User.getRole()%></b>
	</div>
</div>
<div class="logout">
	<form name="logoutForm" action="logoutServlet" method="post">
		<button>Logout</button>
	</form>
</div>
</header>
</body>
</html>