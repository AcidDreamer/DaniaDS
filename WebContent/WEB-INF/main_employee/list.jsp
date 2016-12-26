<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="java.util.ArrayList"%>
<%@page import="bean.application"%>
<%@page import="bean.client"%>

<link href="css/main_admin.css" rel="stylesheet" type="text/css" />
<link href="css/list.css" rel="stylesheet" type="text/css" />

<%
	//Δημιουργεία αντικειμένων και φόρτωμα των cookies

	client Client;
	application Application;
	ArrayList<application> appList = (ArrayList<application>) session.getAttribute("appList");
	ArrayList<client> clientList = (ArrayList<client>) session.getAttribute("clientList");
	ArrayList<application> appListApproved = (ArrayList<application>) session.getAttribute("appListApproved");
	ArrayList<application> appListDisproved = (ArrayList<application>) session.getAttribute("appListDisproved");
	ArrayList<application> appListOnline = (ArrayList<application>) session.getAttribute("appListOnline");
%>
</head>
<body>
	<%@ include file='../resources/header/header.jsp'%>
	<ul class="tab">
		<li><a href="javascript:void(0)" class="tablinks"
			onclick="openTab(event, 'Client')">Clients</a></li>
		<li><a href="javascript:void(0)" class="tablinks"
			onclick="openTab(event, 'Pending_Applications')">Pending
				Applications</a></li>
		<li><a href="javascript:void(0)" class="tablinks"
			onclick="openTab(event, 'Approved_Applications')">Approved
				Applications</a></li>
		<li><a href="javascript:void(0)" class="tablinks"
			onclick="openTab(event, 'Disproved_Applications')">Unapproved
				Applications</a></li>
		<li><a href="javascript:void(0)" class="tablinks"
			onclick="openTab(event, 'OnlineApplications')">Online
				Applications</a></li>

	</ul>


	<div id="Client" class="tabcontent">
		<h3>Clients</h3>
		<%
			if (session.getAttribute("clientList") == null) {

			} else {
				if (!clientList.isEmpty()) {
					for (int i = 0; i < clientList.size(); i++) {
						Client = clientList.get(i);
						out.println("<div class=\"makeMePretty\">");
						out.println("Full name : " + Client.getFullname() + "<br>");
						out.println("ID : " + Client.getId() + "<br>");
						out.println("Username : " + Client.getUsername() + "<br>");
						out.println("Phone : " + Client.getPhone() + "<br>");
						out.println("Salary : " + Client.getSalary() + "<br>");
						out.println("AFM : " + Client.getAfm() + "<br>");
						out.println("ADT : " + Client.getAdt() + "<br>");
						out.println("</div>");
					}
				} else {
					out.println("<b>No applications are currently available.</b>");
				}
			}
		%>

	</div>

	<div id="Pending_Applications" class="tabcontent">
		<h3>Pending Applications</h3>
		<%
			if (session.getAttribute("appList") == null) {
			} else {
				if (!appList.isEmpty()) {
					for (int i = 0; i < appList.size(); i++) {
						Application = appList.get(i);
						out.println(
								"<div class=\"makeMePretty\">Application Code : " + Application.getApp_code() + "<br>");
						out.println(" Client : " + Application.getUsername() + "<br>");
						out.println(" Drivers License : " + Application.getDrivers_licence() + "<br>");
						out.println(" Taxes : " + Application.getTaxes() + "<br>");
						out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
						out.println(" Amount : " + Application.getAmount() + "<br>");
						out.println(" Repay Time  : " + Application.getRepayTime() + " years " + "<br>");
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br>");
						out.println("Modifications : " + Application.getTekmiriwsiEdit() + "<br></div>");
					}
				} else {
					out.println("<b>No applications are currently available.</b>");
				}
			}
		%>

	</div>

	<div id="Approved_Applications" class="tabcontent">
		<h3>Approved Applications</h3>
		<%
			if (session.getAttribute("appListApproved") == null) {
			} else {
				if (!appListApproved.isEmpty()) {
					for (int i = 0; i < appListApproved.size(); i++) {
						Application = appListApproved.get(i);
						out.println(
								"<div class=\"makeMePretty\">Application Code : " + Application.getApp_code() + "<br>");
						out.println(" Client : " + Application.getUsername() + "<br>");
						out.println(" Drivers License : " + Application.getDrivers_licence() + "<br>");
						out.println(" Taxes : " + Application.getTaxes() + "<br>");
						out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
						out.println(" Amount : " + Application.getAmount() + "<br>");
						out.println(" Repay Time  : " + Application.getRepayTime() + " years " + "<br>");
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br>");
						out.println("Modifications : " + Application.getTekmiriwsiEdit() + "<br></div> ");
					}
				} else {
					out.println("<b>No approved applications.</b>");
				}
			}
		%>

	</div>
	<div id="Disproved_Applications" class="tabcontent">
		<h3>Unapproved Applications</h3>
		<%
			if (session.getAttribute("appListDisproved") == null) {
			} else {
				if (!appListDisproved.isEmpty()) {
					for (int i = 0; i < appListDisproved.size(); i++) {
						Application = appListDisproved.get(i);
						out.println(
								"<div class=\"makeMePretty\">Application Code : " + Application.getApp_code() + "<br>");
						out.println(" Client : " + Application.getUsername() + "<br>");
						out.println(" Drivers License : " + Application.getDrivers_licence() + "<br>");
						out.println(" Taxes : " + Application.getTaxes() + "<br>");
						out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
						out.println(" Amount : " + Application.getAmount() + "<br>");
						out.println(" Repay Time  : " + Application.getRepayTime() + " years " + "<br>");
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br>");
						out.println("Modifications : " + Application.getTekmiriwsiEdit() + "<br></div> ");

					}
				} else {
					out.println("<b>No  disproved applications.</b>");
				}
			}
		%>

	</div>
	<div id="OnlineApplications" class="tabcontent">
		<h3>Online Applications</h3>
		<%
			if (session.getAttribute("appListOnline") == null) {
			} else {
				if (!appListOnline.isEmpty()) {
					for (int i = 0; i < appListOnline.size(); i++) {
						Application = appListOnline.get(i);
						out.println(
								"<div class=\"makeMePretty\">Application Code : " + Application.getApp_code() + "<br>");
						out.println(" Client : " + Application.getUsername() + "<br>");
						out.println(" Drivers License : " + Application.getDrivers_licence() + "<br>");
						out.println(" Taxes : " + Application.getTaxes() + "<br>");
						out.println(" Buy Type : " + Application.getBuy_Type() + "<br>");
						out.println(" Amount : " + Application.getAmount() + "<br>");
						out.println(" Repay Time  : " + Application.getRepayTime() + " years " + "<br>");
						out.println("Commentary : ");
						out.println("<form class=\"approveOnline\" method=\"post\">");
						out.println(" <input type=\"text\" name=\"moreCommentary\" placeholder=\"Commentary \"><br>");
						out.println("<input type=\"hidden\" name=\"app_code\" value=\"" + Application.getApp_code()
								+ "\" />");
						out.println("<input type=\"hidden\" name=\"username\" value=\"" + Application.getUsername()
								+ "\" />");
						out.println("  <input type=\"radio\" name=\"approval\" value=\"approve\"> Approve");
						out.println("  <input type=\"radio\" name=\"approval\" value=\"disapprove\"> Disapprove<br>");
						out.println("<button>Submit</button>");
						out.println("</form>");

					}
				} else {
					out.println("<b>No  online applications.</b>");
				}
			}
		%>

	</div>


</body>

<script type="text/javascript">
	//Αλλαγή tab
	function openTab(evt, tabName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}

		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");
		}

		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.className += " active";
	}
</script>
</html>