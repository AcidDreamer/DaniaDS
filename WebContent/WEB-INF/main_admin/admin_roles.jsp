<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file='../resources/header/header.jsp'%>
<link href="css/main_admin.css" rel="stylesheet" type="text/css" />
<%@page import="bean.roles"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<%
	roles Roles = (roles) session.getAttribute("Roles");
%>
<body>
	<!-- Epilegw ti allagi tha kanw -->
	<div class="Option">
		<h1>Select Action</h1>
		<button class="AddBtn">Add Role</button>
		<button class="EditBtn">Edit Role</button>
		<button class="DeleteBtn">Remove Role</button>
	</div>
	<div class="Display">

		<!-- Prosthetw xristi -->
		<div class="Add">
			<form class="RoleForm" action="roleAdmin" method="post">
				Role Name:<input type="text" value="" placeholder="Role Name"
					name="rolename" /><br> Check:<input type="checkbox"
					name="Elegxos"> Registration:<input type="checkbox"
					name="Kataxwrisi"><br> Lists:<input
					type="checkbox" name="Ypologismos"> Modification:<input
					type="checkbox" name="Tropopoiisi"><br> Approval:<input
					type="checkbox" name="Egkrisi"><br>
				<button name="add">Submit</button>
			</form>
		</div>

		<div class="Edit">
			<form class="RoleForm" action="roleAdmin" method="post">
				Role Name:<%=Roles.getRolesSelect()%><br>  Check:<input type="checkbox"
					name="Elegxos"> Registration:<input type="checkbox"
					name="Kataxwrisi"><br> Lists:<input
					type="checkbox" name="Ypologismos"> Modification:<input
					type="checkbox" name="Tropopoiisi"><br> Approval:<input
					type="checkbox" name="Egkrisi"><br>>
				<button name="add">Submit</button>
			</form>
		</div>

		<div class="Delete">
			<form class="DeleteForm" method="post" action="roleAdmin">
				<div class="TextSpans">
					Role Name:<%=Roles.getRolesSelect()%>
				</div>
				<br>
				<button name="remove">Delete</button>
			</form>
		</div>
	</div>

	<script>
		//hide elements on start-up
		$(document).ready(function() {
			$(".Delete,.Edit,.Add").hide();
		});

		//Menu Buttons

		$(".AddBtn").click(function(event) {
			$(".Delete,.Edit").hide(500);
			$(".Add").show(500);
		});

		$(".EditBtn").click(function(event) {
			$(".Delete,.Add").hide(500);
			$(".Edit").show(500);
		});

		$(".DeleteBtn").click(function(event) {
			$(".Edit,.Add").hide(500);
			$(".Delete").show(500);
		});
	</script>


</body>
</html>
