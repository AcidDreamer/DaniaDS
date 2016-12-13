<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="java.util.ArrayList"%>
<%@page import="bean.application"%>

<link href="css/main_admin.css" rel="stylesheet" type="text/css" />
<style>
.CustomersInfo {
	text-align: center;
	font-size: 18px;
	width: 90%;
	padding: 20px;
	background: #fff;
	border-radius: 5px;
	border: 5px solid #7c8c7d;
	color: black;
	float:left;
	margin-left:2%;
	
}
.CustomersInfo .approveClass{
	text-align:left;
	font-size:14px;
	border : 2px solid #7c8c7d;
	margin:1% 0% 1% 0%;

}

.CustomersInfo button {
    width: 20%;
    height: 30px;
    background: #7c8c7d;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #7c8c7d;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 14px;
    font-family: Montserrat;
    outline: none;
    cursor: pointer;
	margin-left:10%;
	margin-bottom:1%;
	
}

</style>
<%
		application Application;
		ArrayList<application> appList = (ArrayList<application>) session.getAttribute("appList");
%>
</head>
<body>
<%@ include file='../resources/header/header.jsp'%>
	<div class="CustomersInfo" style="display: inline;">
	
		<%
		if (session.getAttribute("appList") == null) {
			out.println("<b>No applications are currently available.</b>");
		} else {
	
			for (int i = 0; i < appList.size(); i++) {
				Application = appList.get(i);
				out.println("<form  class=\"approveClass\" method=\"post\" action=\"approveServlet\">");
				out.println("Application Code : " + Application.getApp_code() + "<br>");
				out.println(" Client : " + Application.getUsername() + "<br>");
				out.println(" Drivers License : " + Application.getDrivers_licence()+ "<br>");
				out.println(" Taxes : " + Application.getTaxes()+ "<br>");
				out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
				out.println(" Amount : " + Application.getAmount() + "<br>");
				out.println(" Repay Time  : " + Application.getRepayTime() + " years "+ "<br>");
				out.println("Commentary : " + Application.getTekmiriwsi() + "<br><br>");
				out.println("  <input type=\"radio\" name=\"approval\" value=\"approve\"> Approve");
				out.println("  <input type=\"radio\" name=\"approval\" value=\"disapprove\"> Disapprove");
				out.println("<input type=\"hidden\" name=\"app_code\" value=\""+Application.getApp_code() + "\" />");
				out.println("<input type=\"hidden\" name=\"username\" value=\""+Application.getUsername() + "\" />");
				out.println("<button>Submit</button>");
				out.println("</form>");

			}
		}
		%>
	</div>

</body>
</html>