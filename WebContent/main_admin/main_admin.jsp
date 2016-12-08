<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="main_admin.css" rel="stylesheet" type="text/css"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>

	<div class="Option">
		    <h1>Select Action</h1>
				<button class="AddBtn">Add User</button>		    	
				<button class="EditBtn">Edit User</button>
			    <button class="DeleteBtn">Remove User</button>
			    <button>Logout</button>
	</div>
	<div class="Display">
		<div class="Add">
				Select account type : 
				<select>
					<option>Customer</option>
					<option>Loan Administrator</option>
					<option>Manager</option>
				</select>
				<button>Select</button>
				<form class="UserForm">
					Username:   <input type="text" value="" placeholder="Username" id="username" />
					Password:   <input type="text" value="" placeholder="Password" id="password" /><br>
					Status:     <input type="text" value="" placeholder="Status" id="status" />
					Full name:  <input type="text" value="" placeholder="Full name" id="full_name" /><br>
					ATM:  		<input type="text" value="" placeholder="ATM" id="atm" />
					IDN:  		<input type="text" value="" placeholder="IDN" id="adt" /><br>
					Salary:  	<input type="text" value="" placeholder="Salary" id="salary" />
					Phone: 		<input type="text" value="" placeholder="Phone" id="phone" />
				</form>
				<form class="AdminForm">
					Username:   <input type="text" value="" placeholder="Username" id="username" />
					Password:   <input type="text" value="" placeholder="Password" id="password" /><br>
					Status:     <input type="text" value="" placeholder="Status" id="status" />
					Full name:  <input type="text" value="" placeholder="Full name" id="full_name" /><br>
					ID: 		<input type="text" value="" placeholder="ID" id="id" />
				</form>
				
		</div>
		<div class="Edit ">s
			Im class edit
		</div>
		<div class="Delete">
			Im class delete
		</div>
	</div>
	
<script>
$(document).ready(function () {             
	$(".Delete,.Edit,.Add").hide();
}); 

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
</script>

</body>
</html>