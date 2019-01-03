<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
     
<c:choose> 
	<c:when test="${not empty requestScope.products}">
		<form:form method="post" action="chart.html"> 
		<div class="form-group"> 		
			CHART
		</div>		
			
		</form:form>
	</c:when>
	<c:otherwise>
		<p>EZ DAU PRODUKTUIK</p>
	</c:otherwise>

</c:choose>  