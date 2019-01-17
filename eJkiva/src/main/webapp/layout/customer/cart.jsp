<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<link rel="stylesheet" href="css/unitProduct.css">
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
						<form:form method="post" action="customer.html">
							<div class="form-group">
								<div class="list-group list-group-flush" id="products">
									<c:forEach items="${requestScope.products}" var="product">


										<div class="row" id="ads">
											<div class="col-md-4">
												<div class="card rounded">
													<div class="card-image">
														<span class="card-notify-badge">Low KMS</span> <span
															class="card-notify-year">2018</span> <img
															class="img-fluid"
															src="https://imageonthefly.autodatadirect.com/images/?USER=eDealer&PW=edealer872&IMG=USC80HOC011A021001.jpg&width=440&height=262"
															alt="Alternate Text" />
													</div>
													<div class="card-image-overlay m-auto">
														<span class="card-detail-badge">Used</span> <span
															class="card-detail-badge">$28,000.00</span> <span
															class="card-detail-badge">13000 Kms</span>
													</div>
													<div class="card-body text-center">
														<div class="ad-title m-auto">
															<h5>Honda Accord LX</h5>
														</div>
														<a class="ad-btn" href="#">View</a>
													</div>
													<div class="card-body text-center">
														<input type="submit" id="login-submit" tabindex="4"
															class="form-control btn btn-login" name="action"
															value="Delete">
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
				Cart <a class="btn btn-sm btn-primary"
					href="/eJkiva/customer/cart.html">${fn:length(sessionScope.cart)}</a>

			</div>
		</c:if>

	</div>
	<div class="card-body text-center">
		<input type="submit" id="login-submit" tabindex="4"
			class="form-control btn btn-login" name="action" value="Tramitar">
	</div>
</div>
<form action="cart.html">
<h1>CART!</h1>
</form>