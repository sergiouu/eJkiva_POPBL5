<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
<h2>Enter information</h2> 
<form:form method="post" action="login.html">           
	<div class="form-group">
		<div class="row">
		    <form:label path="uname">Username</form:label>
			<form:input path="uname" class="form-control"/> 
		</div>         
		<div class="row">
			<form:label path="password">Password</form:label>    
			<form:password path="password" class="form-control" />     
		</div>
		<br/>
		<button type="submit" value="login" class="btn btn-primary"/>Login</button>   
	</div>
	
		       
</form:form>   
