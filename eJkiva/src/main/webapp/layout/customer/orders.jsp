<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<c:choose> 
	<c:when test="${not empty requestScope.orders}">
		<form:form method="post" action="orders.html">
			<h1>Your orders</h1>
			<div class="accordion" id="accordionExample">
			<c:forEach items="${requestScope.orders}" var="order">
				<div class="card">
					<div class="card-header" id="headingOne">
						<h5 class="mb-0">
							<button class="btn btn-link" type="button" data-toggle="collapse"
								data-target="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne"><c:out value="${order.orderId}"/></button>
						</h5>
						<button type="button" class='btn btn-primary'>More</button>
					</div>

					<div id="collapseOne" class="collapse show"
						aria-labelledby="headingOne" data-parent="#accordionExample">
						<div class="card-body">
						<c:out value="${order.user.userId}"/>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>

		</form:form>
	</c:when>
	<c:otherwise>
		<p>YOU DON'T HAVE ANY ORDER</p>
	</c:otherwise>

</c:choose> 

<div class="container">
  <h2>Collapsible Methods</h2>
  <p>The toggle method toggles the collapsible content.</p>
  <p>The show method shows the collapsible content.</p>
  <p>The hide method hides the collapsible content.</p>
  <button type="button" class="btn btn-primary">Toggle</button>
  <button type="button" class="btn btn-success">Show</button>
  <button type="button" class="btn btn-warning">Hide</button> 
    
  <div class="collapse">
    Lorem ipsum dolor sit amet, consectetur adipisicing elit,
    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
  </div>
</div>

<script>
$(document).ready(function(){
  $(".btn-primary").click(function(){
    $(".collapse").collapse('toggle');
  });
  $(".btn-success").click(function(){
    $(".collapse").collapse('show');
  });
  $(".btn-warning").click(function(){
    $(".collapse").collapse('hide');
  });
});
</script>