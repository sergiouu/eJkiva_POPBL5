
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
          <a class="nav-link" href="/eJkiva/manager.html">Home <span class="sr-only">(current)</span></a>
        </li>        
        <li class="nav-item">
          <a class="nav-link" href="/eJkiva/manager/orders.html">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="/eJkiva/manager/stock.html">Stock</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/eJkiva/manager/sales.html">Sales</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/eJkiva/manager/history.html">Warehouse history</a>
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
  
  