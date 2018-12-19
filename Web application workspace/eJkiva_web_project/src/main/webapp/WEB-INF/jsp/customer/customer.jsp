<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<html>    
<head>    
    <title><tiles:insertAttribute name="title" ignore="true" /></title>    
</head>    
<body>    
	<h2>eJkiva</h2>   
<c:choose> 
	<c:when test="${not empty requestScope.products}">
		<form:form method="post" action="customer.html">  
		
			<h1>Products</h1>
		
			<c:forEach items="${requestScope.products}" var="product">
				<article class="product">
					<h3><c:out value="${product.prodName}"/></h3>
					<p><c:out value="${product.price}"/></p>
					<!-- BEIDAU NEWSITEM -->
				</article>
			</c:forEach>
			
			
		</form:form>
	</c:when>
	<c:otherwise>
		<p>EZ DAU PRODUKTUIK</p>
	</c:otherwise>

</c:choose>  
</body>    
</html> 