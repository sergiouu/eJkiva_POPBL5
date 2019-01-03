<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<html>    
<head>    
    <title><tiles:insertAttribute name="title" ignore="true" /></title>    
</head>    
<body>      
	<form:form method="get" action="operator.html"> 
	  	<button type="submit" name="action" value="orders"/>Orders</button><hr/>
		<button type="submit" name="action" value="stock">Stock</button><hr/>
		<button type="submit" name="action" value="warehouse">Visualization of warehouse</button>
	</form:form>
</body>    
</html> 