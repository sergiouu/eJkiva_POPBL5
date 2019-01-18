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
	<div class="wrapper-shop-items col-lg-11">
		<div class="mbr-gallery-row">
			<div>
				<div class="shop-items">
					<c:if test="${not empty requestScope.products}">
						<form:form method="post" action="cart.html">
							<div class="form-group">
								<div class="list-group list-group-flush" id="productsCart">
									<c:forEach items="${requestScope.products}" var="product">


										<div class="row" id="ads">
											<div class="col-lg-12">
												<div class="card rounded">
													<div class="card-image">
														<span class="card-notify-year">New!</span> <img
															class="img-fluid" alt="No image"
															src="../img/${product.image}" />
													</div>
													<div class="card-image-overlay m-auto">
														<span class="card-detail-badge">${product.price}&euro;</span>
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
															value="${product.productId}">Delete</button>
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
						<div class="col col-lg-12 center-block" style="padding: 0;">
							<div class="col col-lg-12 centered">
								<p class="display-2">You don't have any product selected
									yet!</p>
							</div>
							<br>
							<div class="col col-lg-2 center-block" style="margin: 0;">
								<a class="btn btn-sm btn-primary" href="/eJkiva/customer.html">Back</a>
							</div>


						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${not empty sessionScope.cart}">
		<div class="col-lg-1 sidebar">
			<form:form method="post" action="cart.html">
				<div class="price-range mbr-section-btn" buttons="0">
					<p>Total items:</p>
					<div class="row">
						<p class="btn btn-sm btn-primary pull-right">${requestScope.total}</p>
						<button type="submit" id="login-submit" tabindex="4"
							class="form-control btn btn-login" name="buy" value="buy">Buy</button>
					</div>

				</div>
			</form:form>

		</div>
	</c:if>
</div>