<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file='../resources/header/header.jsp'%>
<link href="css/main_employee.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id='search'>
		<form class="search" method="post" action="sentApplication">
			Customers Name: <input type="text" value=""
				placeholder='Username' name="username" /> Application ID: <input
				type="text" value="" placeholder='Application ID' name="app_code" />
			Amount Requested: <input type="text" value=""
				placeholder='Amount' name="amount" /><br>
			<br> Repay Time: <input type="text" value=""
				placeholder='Repay Time' name="repayTime" />Buy 
			Type: <select name="buy_type"><option
					value=1>Used</option>
				<option value=2>Brand New</option></select><br>
			<br> Drivers License: <input type="text" value=""
				placeholder='Drivers License' name="drivers_licence" /> Taxes : <input
				type="text" value="" placeholder='Taxes' name="taxes" /> Commentary : <input
				type="text" value="" placeholder='Commentary' name="commentary" />
			<button>Submit</button>
		</form>
	</div>
</body>
</html>