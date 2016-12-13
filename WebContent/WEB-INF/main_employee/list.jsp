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
ul.tab {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
    
}

ul.tab li {float: left;}

ul.tab li a {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    transition: 0.3s;
    font-size: 17px;
}

ul.tab li a:hover {background-color: #ddd;}

ul.tab li a:focus, .active {background-color: #ccc;}

.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

</style>
<%
		client Client;
		application Application;
		ArrayList<application> appList = (ArrayList<application>) session.getAttribute("appList");
%>
</head>
<body>
<%@ include file='../resources/header/header.jsp'%>
<ul class="tab">
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'London')">London</a></li>
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'Paris')">Paris</a></li>
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'Tokyo')">Tokyo</a></li>
</ul>

<div id="Client" class="tabcontent">
  <h3>Client</h3>
  <p>London is the capital city of England.</p>
</div>

<div id="Pending Applications" class="tabcontent">
  <h3>Pending Applications</h3>
  <p>Paris is the capital of France.</p> 
</div>

<div id="Approved Applications" class="tabcontent">
  <h3>Approved Applications</h3>
  <p>Tokyo is the capital of Japan.</p>
</div>
<div id="Disproved Applications" class="tabcontent">
  <h3>Approved Applications</h3>
  <p>Tokyo is the capital of Japan.</p>
</div>

</body>

<script type="text/javascript">
function openCity(evt, cityName) {
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
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

</script>
</html>