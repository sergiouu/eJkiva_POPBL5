<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    
<c:choose> 
	<c:when test="${not empty requestScope.orders}">
		<form:form method="post" action="customer.html"> 
		<div class="form-group"> 		
			<div class="list-group list-group-flush">
				<h1>Your orders</h1>
			
				<c:forEach items="${requestScope.orders}" var="order">
					<article class="list-group-item">
						<h3><c:out value="${order.orderId}"/></h3>
						<p><c:out value="${order.userId}"/></p>
						<!-- BEIDAU NEWSITEM -->
					</article>
				</c:forEach>
			
			</div>
		</div>		
			
		</form:form>
	</c:when>
	<c:otherwise>
		<p>YOU DON'T HAVE ANY ORDERt</p>
	</c:otherwise>

</c:choose> 