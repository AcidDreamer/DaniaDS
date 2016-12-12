<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file='../resources/header/header.jsp'%>
<link href="css/main_employee.css" rel="stylesheet" type="text/css" />
<style>
.CustomersInfo {
	text-align: center;
	font-size: 14px;
	width: 100%;
	padding: 20px;
	background: #fff;
	border-radius: 5px;
	border-top: 5px solid #7c8c7d;
	margin: 0 auto;
	color: black;
}
</style>

</head>
<body>
	<div id='search'>
		<form class="search" method="post" action="searchCustomer">
			Customers ID: <input type="text" value=""
				placeholder='ID , AFM or ADT' name="searcher" /> Amount Requested:
			<input type="text" value=""
				placeholder='The requested amount of money' name="amount" /> Buy
			Type: <select name="buy_type"><option value=1>Used</option>
				<option value=2> Brand New</option></select><br><br>
			Drivers License: <input type="text" value=""
				placeholder='Drivers License' name="drivers_licence" />
			Taxes : <input type="text" value=""
				placeholder='Drivers License' name="taxes" />
			<button>Submit</button>
		</form>
	</div>
</body>
</html>