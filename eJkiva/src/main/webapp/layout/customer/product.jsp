<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<head>
   <link rel="stylesheet" href="css/unitProduct.css" >
   <link rel="stylesheet" href="../css/customerStyle.css" />
   <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
   <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
   <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>  

    <div class="col-lg-5 " id="ads">
	<div class="card rounded" id="product">
		<c:if test="${not empty requestScope.product}">
			<form:form method="post" action="product.html">
				<div class="card-image">
					<span class="card-notify-badge">${requestScope.product.productName}</span> <span
						class="card-notify-year">${requestScope.product.price}</span> <img class="img-fluid"
						src="../img/${requestScope.product.image}"
						alt="Alternate Text" />
				</div>
				<div class="card-image-overlay m-auto">
					<span class="card-detail-badge">${requestScope.product.price}</span> <span
						class="card-detail-badge">${requestScope.product.productId}</span>
				</div>
				<div class="card-body text-center">
					<div class="ad-title m-auto">
						<h5>${requestScope.product.description}</h5>
					</div>

				</div>
				<div id="buttonsProd">
					<div class="form-group">
						<div class="row" id="anadirX">
							<div class="col-sm-6 col-sm-offset-3">
								<button type="submit" id="register-submit" tabindex="4"
									class="btn btn-outline-success my-2 my-sm-0" name="addProduct"
									value="${product.productId}">Add</button>
							</div>
							<div>
								<input type="number" id="number" name="number" min="1" max="50"  value="1">
							</div>
						</div>
					</div>
					<div>
						<form action="backToProducts.html"
							class="form-inline mt-2 mt-md-0">
							
							<a class="btn btn-outline-success my-2 my-sm-0"
								 href="/eJkiva/customer.html">Back</a>
						</form>
					</div>
				</div>
			</form:form>
		</c:if>
	</div>
</div>