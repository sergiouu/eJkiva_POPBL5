<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><!DOCTYPE html>
<html lang="en">
  <head>
  	<meta charset="utf-8">
	<title>D3 praktika12</title>
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  </head>
  <body>
  <form action="order.html">
  <div class="text-center">
		  <h1>ORDER <c:out value="${requestScope.order.orderId}"/></h1>	
		  <br>
		  <br>
		</div>  
			<div class="card-columns">
			  <c:forEach items="${requestScope.listProduct}" var="registeredProduct">
				<div class="container" class="form-group mb-2">
					<div class="card" style="width:400px">
					  <img class="card-img-top" src="${registeredProduct.image}" style="width:100%">
					  <h2 class="text-center"><c:out value="${registeredProduct.productId}"/> <c:out value="${registeredProduct.product_name}"/></h2>
					  <p id = "status" class="text-center">Status: <c:out value="${registeredProduct.status}"/></p>
					  <p>Quantity: <c:out value="${registeredProduct.quantity}"/></p>
					</div>
					<br>
				</div>
			  </c:forEach>	
			</div>
			<!-- <script>
				status = document.getElementById("status");
				console.log(status);
				if(status.localeCompare('Delivered')==0) document.getElementById("status").style.color = "#green";
				else document.getElementById("status").style.color = "#ff0000";

			</script>-->
  
  </form>
  		
			
  </body>
</html>