<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file ='../resources/header/header.jsp'%>
	<link href="css/main_admin.css" rel="stylesheet" type="text/css"/>
	<%@page import="bean.roles"%>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
	<% 
			roles  Roles = (roles) session.getAttribute("Roles");
	%>
<body>
	<!-- Epilegw ti allagi tha kanw -->
	<div class="Option">
		    <h1>Select Action</h1>
				<button class="AddBtn">Add User</button>		    	
				<button class="EditBtn">Edit User</button>
			    <button class="DeleteBtn">Remove User</button>
	</div>		
	<div class="Display">
	
		<!-- Prosthetw xristi -->
		<div class="Add">
				Select account type : 
				<%=Roles.getRolesSelect() %>
				<button class="SelectBtnAdd">Select</button>
				<!-- Ama einai xristis -->
				<form class="UserForm" action="adminUsers" method="post">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" name="username" /></div>
					<div class = "TextSpans">Password:<input type="text" value="" placeholder="Password" name="password" /></div><br>
					<div class = "TextSpans">Full name:<input type="text" value="" placeholder="Full name" name="full_name" /></div>
					<div class = "TextSpans">E-mail:<input type="text" value="" placeholder="E-mail" name="email" /></div><br>
					<div class = "TextSpans">AFM:<input type="text" value="" placeholder="ATM" name="atm" /></div>
					<div class = "TextSpans">IDN:<input type="text" value="" placeholder="IDN" name="adt" /></div><br>
					<div class = "TextSpans">Salary:<input type="text" value="" placeholder="Salary" name="salary" /></div>
					<div class = "TextSpans">Phone:<input type="text" value="" placeholder="Phone" name="phone" /></div>
					<br><button name="ClientAddBtn">Submit</button>
				</form>
				<!--  Ama einai admin -->
				<form class="AdminForm" action="adminUsers" method="post">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" name="username" /></div>
					<div class = "TextSpans">Password:<input type="text" value="" placeholder="Password" name="password" /></div><br>
					<div class = "TextSpans">Full name:<input type="text" value="" placeholder="Full name" name="full_name" /></div><br>
					Role: <%=Roles.getRolesSelect() %>
					<br><br><button name="OtherAddBtn">Submit</button>
				</form>
				
		</div>
		
		<!--  Kanw edit enan xristi -->
		<div class="Edit">
				Select account type : 
				<!-- epilegw typo xristi -->
				<%=Roles.getRolesSelect() %>
				<button class="SelectBtnEdit">Select</button>
				
				<!--  An einai xristis dinw username , pio pedio tha allaksw kai tin timi -->
				<form class="UserFormEdit" action="adminUsers" method="post" >
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" name="username" />
						Change:
						<!--  Dialegw pio pedio tha allaksi  -->
						<select class="selectToChange" name ="selectToChange">
							<option value=1>Password</option>
							<option value=2>Status</option>
							<option value=3>Full name</option>
							<option value=4>ID</option>
						</select>
					</div>
					<!-- ginete isodos neas timis -->
					<div class = "TextSpans">New Value:<input type="text" value="" placeholder="New Value" name="newValue" /></div>
					<br><button name = "editBtn">Submit</button>					
				</form>
				
				<form class="AdminFormEdit" action="adminUsers" method="post" >
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" name="username" />
						Change:
						<!--  Dialegw pio pedio tha allaksi  -->
						<select class="selectToChange" name ="selectToChange">
							<option value=1>Password</option>
							<option value=2>Role</option>
							<option value=3>Full name</option>
							<option value=4>ATM</option>
							<option value=5>IDN</option>
							<option value=6>Salary</option>
							<option value=7>Phone</option>
						</select>
					</div>
					<!-- ginete isodos neas timis -->
					<div class = "TextSpans">New Value:<input type="text" value="" placeholder="New Value" name="newValue" /></div>
					<br><button name = "editBtn">Submit</button>					
				</form>
				
		</div>
		<div class="Delete">
				<form class="DeleteForm" action="adminUsers" method="post" >
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" name="username" /></div><%=Roles.getRolesSelect() %><br>					
					<br><button name="deleteBtn">Delete</button>
				</form>
		</div>
	</div>
<script>
//hide elements on start-up
$(document).ready(function () {             
	$(".UserForm,.AdminForm,.UserFormEdit,.AdminFormEdit").hide();
}); 

$(document).ready(function () {             
	$(".Delete,.Edit,.Add").hide();
}); 

//Menu Buttons

$( ".AddBtn" ).click(function(event) {
  $( ".Delete,.Edit" ).hide(500);
  $(".Add").show(500);
});

$( ".EditBtn" ).click(function(event) {
	  $( ".Delete,.Add" ).hide(500);
	  $(".Edit").show(500);
});

$( ".DeleteBtn" ).click(function(event) {
	  $( ".Edit,.Add" ).hide(500);
	  $(".Delete").show(500);
});

$( ".SelectBtnAdd" ).click(function(event) {
	if($(".allRoles").val()=="Client"){
		  $( ".AdminForm" ).hide(500);
		  $(".UserForm").show(500);
	}else if($(".allRoles").val()!="Client"){
		  $( ".UserForm" ).hide(500);
		  $(".AdminForm").show(500);
	}
});

$( ".SelectBtnEdit" ).click(function(event) {
	$( ".AdminFormEdit" ).hide(500);
	$( ".UserFormEdit" ).hide(500);
	if($("#Edit > select[name='allRoles']").val()=="Client"){
		  $( ".AdminFormEdit" ).hide(500);
		  $(".UserFormEdit").show(500);
	}else if($("#Edit > select[name='allRoles']").val()!="Client"){
		  $( ".UserFormEdit" ).hide(500);
		  $(".AdminFormEdit").show(500);
	}
});



</script>

</body>
</html>