<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Welcome to Someone's Bank</title>
	<div  class = "login"><form class = "loginForm">
		Username:<input type="text" value="" placeholder="Username" id="usernameHeader" />
		Password:<input type="text" value="" placeholder="Password" id="passwordHeader" />
		<button>Submit</button>
	</form> </div>
	<div class="logout"><button>Logout</button></div>
	<style>
		header{
			color:white;
			background-color: #160bfd ;
		}
		div {
			display:inline;
		}
		.loginForm{
			display:inline;
			clear:left;
		}
		.login {
			width:60%;
		}
		.logout{
			float:right;
			width:20%;
		}
		button{
		    width: 100px;
		    background: #7c8c7d;
		    box-sizing: border-box;
		    border-radius: 5px;
		    border: 1px solid #7c8c7d;
		    color: #fff;
		    font-weight: bold;
		    text-transform: uppercase;
		    font-size: 12px;
		    font-family: Montserrat;
		    outline: none;
		    cursor: pointer;
		}
		button:hover {
		    background: #2c6bd6;
		}
		
		input {
		    text-align: center;
		    box-sizing: border-box;
		    border-radius: 5px;
		    border: 1px solid #ccc;
		    margin-bottom: 2px;
		    font-size: 14px;
		    font-family: Montserrat;
		    outline: none;  
		}
		</style>
</header>
</body>
</html>