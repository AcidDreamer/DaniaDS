<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="java.util.ArrayList"%>
<%@page import="bean.application"%>

<link href="css/main_admin.css" rel="stylesheet" type="text/css" />
<link href="css/editDirector.css" rel="stylesheet" type="text/css" />

<%
	application Application;
	ArrayList<application> appList = (ArrayList<application>) session.getAttribute("appList");
%>
</head>
<body>
	<%@ include file='../resources/header/header.jsp'%>
	<div class="CustomersInfo" style="display: inline;">

		<%
		//Φορτώνουμε όλες τις εκκρεμούσες αιτήσεις με 2 radio buttons
			if (session.getAttribute("appList") == null) {
			} else {
				if (!appList.isEmpty()) {
					for (int i = 0; i < appList.size(); i++) {
						Application = appList.get(i);
						out.println("<form  class=\"approveClass\" method=\"post\" action=\"editApplication\">");
						out.println("Application Code : " + Application.getApp_code() + "<br>");
						out.println(" Client : " + Application.getUsername() + "<br>");
						out.println(" Drivers License : " + Application.getDrivers_licence() + "<br>");
						out.println(" Taxes : " + Application.getTaxes() + "<br>");
						out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
						out.println(" Amount : " + Application.getAmount() + "<br>");
						out.println(" Repay Time  : " + Application.getRepayTime() + " years " + "<br>");
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br><br>");
						out.println("Modifications : " + Application.getTekmiriwsiEdit() + "<br><br>Change the amount of the loan :<br> ");
						out.println("  <input type=\"text\" name=\"newAmount\" placeholder=\"New amount\"><br>Provide some extra commentary : <br><br> ");
						out.println("  <input type=\"text\" name=\"newCommentary\" placeholder=\"New amount\"><br> ");
						out.println("<input type=\"hidden\" name=\"app_code\" value=\"" + Application.getApp_code()
								+ "\" />");
						out.println("<input type=\"hidden\" name=\"username\" value=\"" + Application.getUsername()
								+ "\" />");
						out.println("<button>Submit</button>");
						out.println("</form>");

					}
				} else {
					out.println("<b>No applications are currently available.</b>");
				}
			}
		%>
	</div>
</body>
</html>