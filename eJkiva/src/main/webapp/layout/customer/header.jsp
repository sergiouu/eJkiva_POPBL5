<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/customerStyle.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
  <!-- <nav class="navbar navbar-expand-md navbar-dark navbar-fixed-top bg-dark"> -->
  <nav class="navbar navbar-expand-md navbar-dark navbar-fixed-top bg-dark">
    <a class="navbar-brand" href="#">eJkiva</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <c:if test="${not empty sessionScope.user }">
		<fmt:bundle basename="resources.View">
		<div><p class="navbar-brand">${sessionScope.user.username }</p></div>			
		</fmt:bundle>
	</c:if>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/eJkiva/customer.html">Home <span class="sr-only">(current)</span></a>
        </li>        
        <li class="nav-item">
          <a class="nav-link" href="/eJkiva/customer/productHistory.html">Product history</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="/eJkiva/customer/cart.html">My cart</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/eJkiva/customer/orders.html">My orders history</a>
        </li>
      </ul>
      <c:if test="${not empty sessionScope.user}">
			<div class="logout">
				<form action="login.html" class="form-inline mt-2 mt-md-0">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="logout">Logout</button>
				</form>
			</div>
		</c:if>

    </div>
  </nav>
  
  