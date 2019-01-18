<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row col-lg-12">
	<div class="wrapper-shop-items col-lg-9">
		<div class="mbr-gallery-row">
			<div>
				<div class="shop-items">
					<c:if test="${not empty requestScope.products}">
						<form:form method="post" action="stock.html">
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
</div>