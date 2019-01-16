<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/customerStyle.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<div class="row col-lg-12">
	<div class="wrapper-shop-items col-lg-9">
		<div class="mbr-gallery-row">
			<div>
				<div class="shop-items">
					<c:if test="${not empty requestScope.products}">
						<form:form method="post" action="customer.html">
						<div class="form-group">
							<div class="list-group list-group-flush" id="products">
								<c:forEach items="${requestScope.products}" var="product">
									<div class="mbr-gallery-item" id="product">
										<div class="item_overlay" data-toggle="modal"></div>
										<div class="galleryItem" data-toggle="modal">
											<div class="style_overlay"></div>
											<div class="img_wraper">
												<img alt="" src="assets/images/shop0.jpg">
											</div>
											<span class="onsale mbr-fonts-style display-7"
												data-onsale="false" style="display: none;">-50%</span>
											<div class="sidebar_wraper">
												<h4 class="item-title mbr-fonts-style mbr-text display-5">${product.productName}
												</h4>
												<div class="price-block">
													<span class="shop-item-price mbr-fonts-style display-5">${product.price}</span><span
														class="oldprice mbr-fonts-style display-7"
														style="display: none;">${product.price} </span>
												</div>
												<div class="card-description">
													${product.description}<br> <br>
												</div>
															
												<div class="form-group">
													<div class="row">
														<div class="col-sm-2 col-sm-offset-3">
															<button type="submit" id="register-submit" tabindex="4"
																class="btn btn-secondary" name="addProduct" 
																value="${product.productId}">Add</button>
														</div>
														
														<div class="col-sm-2 col-sm-offset-3">
															<button type="submit" id="register-submit" tabindex="4"
																class="btn btn-secondary" name="product"
																value="${product.productId}">More</button>
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>

								</c:forEach>

							</div>
						</div>
						</form:form>
					</c:if>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="col-xl-3 sidebar">
		<c:if test="${not empty sessionScope.cart}">
			<div class="price-range mbr-section-btn" buttons="0">
				Cart <button class="btn btn-sm btn-secondary" href="/eJkiva/customer/cart.html">${fn:length(sessionScope.cart)}</button>
				<p alt="" class="rounded-circle" style="background-color:red; color:white;">${fn:length(sessionScope.cart)}</p>
			</div>
		</c:if>
		
	</div>
</div>
