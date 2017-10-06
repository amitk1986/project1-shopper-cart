<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Online Store</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Online Store</h1>      
    <p>Mission, Vision & Values</p>
	<p style="color:red">${errorMsg}</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>
				<c:set var="userNameVal" value='<%=session.getAttribute("userName")%>'/>
			<c:choose>
				<c:when test="${userNameVal!='' && userNameVal != null}">
					<a  href="#"><span class="glyphicon glyphicon-user"></span> Welcome ${userNameVal} </a>
				</c:when>
				<c:otherwise>
					<a data-target="#loginmodal" data-toggle="modal"  href="#loginmodal"><span class="glyphicon glyphicon-user"></span> Your Account</a>
				</c:otherwise>
			</c:choose>		
		</li>
		<li>
			<c:if test="${userNameVal!='' && userNameVal != null}">
					<a href="logout">Log Out</a>
			</c:if>
		</li>
        <li><a href="viewCart?viewCart=yes"><span class="glyphicon glyphicon-shopping-cart"></span> Cart(<%=session.getAttribute("qty")%>)</a></li>
      </ul>
    </div>
  </div>
</nav>

	  
	  
  	