<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file ='../resources/header/header.jsp'%>
<%@page import="bean.client"%>

<link href="css/main_employee.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id='search'>
<form class="search" method="post" action = "searchCustomer">
	Search by customer's Password,AFM or ADT<br><select name="searchBy"><option value=1>ID</option><option value=2>AFM</option><option value=3>ADT</option></select>
	<input type="text" value="" placeholder='ID , AFM or ADT' name="searcher" />
	<button>Submit</button>
</form>
</div>
<div class="CustomersInfo">
<%
client Client = (client) session.getAttribute("Client");
if (session.getAttribute("Client") != null) {
%> <%=Client.toString()%>
<p><%=Client.giveLoan()%></p>
<%         
}else{
	out.print("No customer with such ID,AFM,ADT");
}
%>
</div>
</body>
<footer>
<style>
.CustomersInfo{
    text-align: center;
    font-size:18px;
    width: 100%;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    border-top: 5px solid #7c8c7d;
    margin: 50px 0px 0px 0px;
    color:black;

}
.CustomersInfo p{
	padding-top:1%;
	padding-bottom:1%;
	font-size:18px;
	border-radius: 5px;
    border:5px solid #7c8c7d;
	
}
</style>
</footer>
</html>