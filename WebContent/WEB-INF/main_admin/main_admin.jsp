<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file ='../resources/header/header.jsp'%>
	<link href="css/main_admin.css" rel="stylesheet" type="text/css"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
	<!-- Epilegw ti allagi tha kanw -->
	<div class="Option">
		    <h1>Select Action</h1>
				<button class="AddBtn">Add User</button>		    	
				<button class="EditBtn">Edit User</button>
			    <button class="DeleteBtn">Remove User</button>
			    <button>Logout</button>
	</div>
	<div class="Display">
	
		<!-- Prosthetw xristi -->
		<div class="Add">
				Select account type : 
				<select class="selectForm">
					<option value=0>Customer</option>
					<option value=1>Loan Administrator</option>
					<option value=1>Manager</option>
				</select>
				<button class="SelectBtnAdd">Select</button>
				<!-- Ama einai xristis -->
				<form class="UserForm">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" id="username" /></div>
					<div class = "TextSpans">Password:<input type="text" value="" placeholder="Password" id="password" /></div><br>
					<div class = "TextSpans">Status:<input type="text" value="" placeholder="Status" id="status" /></div>
					<div class = "TextSpans">Full name:<input type="text" value="" placeholder="Full name" id="full_name" /></div><br>
					<div class = "TextSpans">ATM:<input type="text" value="" placeholder="ATM" id="atm" /></div>
					<div class = "TextSpans">IDN:<input type="text" value="" placeholder="IDN" id="adt" /></div><br>
					<div class = "TextSpans">Salary:<input type="text" value="" placeholder="Salary" id="salary" /></div>
					<div class = "TextSpans">Phone:<input type="text" value="" placeholder="Phone" id="phone" /></div>
					<br><button>Submit</button>
				</form>
				<!--  Ama einai admin -->
				<form class="AdminForm">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" id="username" /></div>
					<div class = "TextSpans">Password:<input type="text" value="" placeholder="Password" id="password" /></div><br>
					<div class = "TextSpans">Status:<input type="text" value="" placeholder="Status" id="status" /></div>
					<div class = "TextSpans">Full name:<input type="text" value="" placeholder="Full name" id="full_name" /></div><br>
					<div class = "TextSpans">ID:<input type="text" value="" placeholder="ID" id="id" /></div>
					<br><button>Submit</button>
				</form>
				
		</div>
		
		<!--  Kanw edit enan xristi -->
		<div class="Edit">
				Select account type : 
				<!-- epilegw typo xristi -->
				<select class="selectFormEdit">
					<option value=0>Customer</option>
					<option value=1>Loan Administrator</option>
					<option value=1>Manager</option>
				</select>
				<button class="SelectBtnEdit">Select</button>
				
				<!--  An einai xristis dinw username , pio pedio tha allaksw kai tin timi -->
				<form class="UserFormEdit">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" id="username" />
						Change:
						<!--  Dialegw pio pedio tha allaksi  -->
						<select class="selectToChange">
							<option value=0>Username</option>
							<option value=1>Password</option>
							<option value=2>Status</option>
							<option value=3>Full name</option>
							<option value=4>ID</option>
						</select>
					</div>
					<!-- ginete isodos neas timis -->
					<div class = "TextSpans">New Value:<input type="text" value="" placeholder="New Value" id="newValue" /></div>
					<br><button>Submit</button>					
				</form>
				<form class="AdminFormEdit">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" id="username" />
						Change:
						<!--  Dialegw pio pedio tha allaksi  -->
						<select class="selectToChange">
							<option value=0>Username</option>
							<option value=1>Password</option>
							<option value=2>Status</option>
							<option value=3>Full name</option>
							<option value=4>ATM</option>
							<option value=5>IDN</option>
							<option value=6>Salary</option>
							<option value=7>Phone</option>
						</select>
					</div>
					<!-- ginete isodos neas timis -->
					<div class = "TextSpans">New Value:<input type="text" value="" placeholder="New Value" id="newValue" /></div>
					<br><button>Submit</button>					
				</form>
				
		</div>
		<div class="Delete">
				<form class="DeleteForm">
					<div class = "TextSpans">Username:<input type="text" value="" placeholder="Username" id="username" /></div>
					<br><button>Delete</button>
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
	if($(".selectForm").val()==0){
		  $( ".AdminForm" ).hide(500);
		  $(".UserForm").show(500);
	}else if($(".selectForm").val()==1){
		  $( ".UserForm" ).hide(500);
		  $(".AdminForm").show(500);
	}
});

$( ".SelectBtnEdit" ).click(function(event) {
	if($(".selectFormEdit").val()==0){
		  $( ".AdminFormEdit" ).hide(500);
		  $(".UserFormEdit").show(500);
	}else if($(".selectFormEdit").val()==1){
		  $( ".UserFormEdit" ).hide(500);
		  $(".AdminFormEdit").show(500);
	}
});


</script>

</body>
</html>