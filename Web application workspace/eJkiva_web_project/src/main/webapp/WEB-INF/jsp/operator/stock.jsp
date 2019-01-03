<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<html>    
<head>    
    <title><tiles:insertAttribute name="title" ignore="true" /></title>    
</head>    
<body>      
<c:choose> 
	<c:when test="${not empty requestScope.products}">
		<form:form method="post" action="stock.html"> 
		<div class="form-group"> 		
			<div class="list-group list-group-flush">
				<h1>Stock</h1>
			
				<c:forEach items="${requestScope.products}" var="product">
					<article class="list-group-item">
						<h3><c:out value="${product.prodName}"/></h3>
						<p><c:out value="${product.price}"/></p>
						<p><c:out value="${product.departamentID}"/></p>
						<!-- BEIDAU NEWSITEM -->
					</article>
				</c:forEach>
			
			</div>
		</div>		
			
		</form:form>
	</c:when>
	<c:otherwise>
		<p>YOU DON'T HAVE ANY ORDER</p>
	</c:otherwise>

</c:choose>  
</body>    
</html> 