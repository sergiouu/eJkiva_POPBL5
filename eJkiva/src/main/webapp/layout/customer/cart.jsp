<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <link rel="stylesheet" href="../css/customerStyle.css" />
   <link rel="stylesheet" href="../css/unitProduct.css" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<div class="row col-lg-12">
	<div class="wrapper-shop-items col-lg-9">
		<div class="mbr-gallery-row">
			<div>
				<div class="shop-items">
					<c:if test="${not empty requestScope.products}">
						<form:form method="post" action="cart.html">
							<div class="form-group">
								<div class="list-group list-group-flush" id="products">
									<c:forEach items="${requestScope.products}" var="product">


										<div class="row" id="ads">
											<div class="col-lg-12">
												<div class="card rounded">
													<div class="card-image">
														<span class="card-notify-badge">Low KMS</span> <span
															class="card-notify-year">2018</span> 
															<img class="img-fluid" alt="No image" src="../img/${product.image}"/>
													</div>
													<div class="card-image-overlay m-auto">
														<span class="card-detail-badge">${product.price}</span> 
													</div>
													<div class="card-body text-center">
														<div class="ad-title m-auto">
															<h5>${product.productName}</h5>
														</div>
														<div class="ad-title m-auto">
															<h5>${product.description}</h5>
														</div>
														<a class="ad-btn" href="#">View</a>
													</div>
													<div class="card-body text-center">
														<button type="submit" id="login-submit" tabindex="4"
															class="form-control btn btn-login" name="delete"
															value="${product}">Delete</button>
													</div>
												</div>
											</div>
										</div>

									</c:forEach>

								</div>
							</div>
						</form:form>
					</c:if>
					<c:if test="${empty sessionScope.cart}">
						<div class="col col-lg-12 center-block" style="padding:0;">
							<div class="col col-lg-12 centered">
								<p class="display-2">You don't have any product selected yet!</p>
							</div>
							<br>
							<div class="col col-lg-2 center-block" style="margin:0;">
								<a class="btn btn-sm btn-primary"
									href="/eJkiva/customer.html">Back</a>
							</div>
							

						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${not empty sessionScope.cart}">
	<div class="col-xl-3 sidebar">
		
			<div class="price-range mbr-section-btn" buttons="0">
				Cart 
				<a class="btn btn-sm btn-primary"
					href="/eJkiva/customer/cart.html">${fn:length(sessionScope.cart)}</a>

			</div>
		

	</div>
	</c:if>
</div>