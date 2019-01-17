<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<div class="container justify-content-center">
  <c:choose> 
	<c:when test="${not empty requestScope.orders}">
		<form:form method="post" action="orders.html">
		<div class="form-group">
			<h1>Your orders</h1>
			<c:forEach items="${requestScope.orders}" var="order">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header" id="headingOne">
								<div class="form-group">	
									<button type="submit" id="register-submit" tabindex="4"
																class="btn btn-primary float-right display-7" name="order"
																value="${order.orderId}">More</button>							
									<p class="display-4">${order.date}<!--a href="order.html"
											class='btn btn-primary float-right display-7' name="order" value="${order.orderId}">More</a-->
									</p>
								</div>
							</div>

						</div>
					</div>

				</c:forEach>
			<div>
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
			</div>
		</div>
		</form:form>
	</c:when>
	<c:otherwise>
		<p>YOU DON'T HAVE ANY ORDER</p>
	</c:otherwise>

</c:choose> 
</div>

