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

<%
	client Client;
	application Application;
	ArrayList<application> appList = (ArrayList<application>) session.getAttribute("appList");
	ArrayList<client> clientList = (ArrayList<client>) session.getAttribute("clientList");
	ArrayList<application> appListApproved = (ArrayList<application>) session.getAttribute("appListApproved");
	ArrayList<application> appListDisproved = (ArrayList<application>) session.getAttribute("appListDisproved");
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
			onclick="openTab(event, 'Disproved_Applications')">Disproved
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
						out.println("AFM : " + Client.getAdt() + "<br>");
						out.println("ADT : " + Client.getAdt() + "<br>");
						out.println("</div>");
					}
				}else{
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
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br></div>");
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
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br></div>");
					}
				} else {
					out.println("<b>No approved applications.</b>");
				}
			}
		%>

	</div>
	<div id="Disproved_Applications" class="tabcontent">
		<h3>Disproved Applications</h3>
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
						out.println("Commentary : " + Application.getTekmiriwsi() + "<br></div>");
					}
				} else {
					out.println("<b>No applications disproved applications.</b>");
				}
			}
		%>

	</div>

</body>

<script type="text/javascript">
	function openTab(evt, tabName) {
		//Declare vars
		var i, tabcontent, tablinks;

		// Hide all tabcontent 
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}

		// Get tablinks and remove the class active
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");
		}

		// Show the current tab
		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.className += " active";
	}
</script>
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
	float: left;
	margin-left: 2%;
}

.CustomersInfo .approveClass {
	text-align: left;
	font-size: 14px;
	border: 2px solid #7c8c7d;
	margin: 1% 0% 1% 0%;
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
	margin-left: 10%;
	margin-bottom: 1%;
}

ul.tab {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

ul.tab li {
	float: left;
}

ul.tab li a {
	display: inline-block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	transition: 0.3s;
	font-size: 17px;
}

h3 {
	text-align: center;
	color: black;
}

ul.tab li a:hover {
	background-color: #ddd;
}

ul.tab li a:focus, .active {
	background-color: #ccc;
}

.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
	text-align: center;
	color: black;
	background-color: #b5b2a0;
}

.makeMePretty {
	border: 5px solid #fff;
	margin: 1% 0px 1% 0px;
	padding: 1% 0px 1% 0px;
}
</style>
</html>