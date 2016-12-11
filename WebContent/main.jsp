<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/main.css" rel="stylesheet" type="text/css"/>
<%
//allow access only if session exists
if (session.getAttribute("User") == null) {
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
	<button name="Elegxos">Elegxos</button>
	<button name="Kataxwrisi">Kataxwrisi</button>
	<button name="Ypologismos">Ypologismos</button>
	<button name="Tropopoiisi">Tropopoiisi</button>
	<button name="Egkrisi">Egkrisi</button>
	<button name="DiaxeirisiXristwn">Diaxeirisi Xristwn</button>
	<button name="DiaxeirisiRolwn">Diaxeirisi Rolwn</button>
	<button name="DiaxeirisiYpiresiwn">Diaxeirisi Ypiresiwn</button>
	</form>
</div>

</body>
</html>