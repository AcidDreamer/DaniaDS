<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/main.css" rel="stylesheet" type="text/css"/>
<%
user User1 = (user) session.getAttribute("User");
if (session.getAttribute("User") == null || User1.getRole().equals("Client") ) {
        response.sendRedirect("index.jsp");
        
}else{%>
<%@ include file ='../WEB-INF/resources/header/header.jsp'%>
<%}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
        for (Cookie cookie : cookies) {
                if (cookie.getName().equals("User")) {
                        userName = cookie.getValue();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                }
        }
}
%>
</head>
<body>
<br><br><br>


<div class = "Services" style = "display:inline;" >
	<form action="checkYourPrivilages" method="post">
	<button name="Elegxos">Check</button>
	<button name="Kataxwrisi">Registration</button>
	<button name="Listes">Lists</button>
	<button name="Tropopoiisi">Modification</button>
	<button name="Egkrisi">Application Approval</button>
	<button name="DiaxeirisiXristwn">User Admin</button>
	<button name="DiaxeirisiRolwn">Role Admin</button>
	<button name="DiaxeirisiYpiresiwn">Services Admin</button>
	</form>
</div>

</body>
</html>