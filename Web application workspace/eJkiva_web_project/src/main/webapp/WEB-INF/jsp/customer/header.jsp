
<!-- Fixed navbar -->
<nav
	class="navbar navbar-expand-md navbar-dark navbar-fixed-top bg-dark">
	<a class="navbar-brand" href="/eJkiva_web_project/customer.html">eJkiva</a>
	<c:if test="${not empty sessionScope.user }">
		<fmt:bundle basename="resources.View">
			<p class="navbar-brand">${sessionScope.user.uname }</p>
		</fmt:bundle>
	</c:if>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="/eJkiva_web_project/customer.html">Home <!-- <span class="sr-only">(current)</span> --></a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
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