<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row col-lg-12">
	<div class="wrapper-shop-items col-lg-9">
		<div class="mbr-gallery-row">
			<div>
				<div class="shop-items">
					<c:if test="${not empty requestScope.products}">
						<form:form method="post" action="customer.html" id="register-form" role="form">
						<div class="form-group">
							<div class="list-group list-group-flush" id="products">
								<c:forEach items="${requestScope.products}" var="product">
												<div class="form-group">
													<input type="text" name="namee" id="name" tabindex="2"
														class="form-control" placeholder="First Name">
												</div>
												<div class="form-group">
													<div class="row">
														<div class="col-sm-6 col-sm-offset-3">
															<input type="submit" id="register-submit" tabindex="4"
																class="form-control btn btn-register" name="action"
																value="${product.productId}">
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
		<div class="sidebar-background"></div>
		<div class="sidebar-block container bestseller-block">
			<h4 class="sidebar-title mbr-fonts-style mbr-text display-7">Best
				Sellers</h4>
			<div class="bestsellers col-md-12" onselectstart="return false"></div>
		</div>
		<div class="sidebar-block container range-slider">
			<h4 class="sidebar-title mbr-fonts-style mbr-text display-7">Price
				Range</h4>
			<div class="filter-cost" onselectstart="return false">
				<div class="price-controls">
					<label class="min-price"><input class="min-input"
						type="text" value="4" name="min" disabled=""></label><label
						class="max-price"><input class="max-input" type="text"
						value="91" name="max" disabled=""></label>
				</div>
				<div class="range-controls">
					<div class="scale">
						<div class="bar"></div>
					</div>
					<div class="toggle min-toggle"></div>
					<div class="toggle max-toggle"></div>
				</div>
			</div>
			<div class="price-range mbr-section-btn" buttons="0">
				<a class="btn btn-sm btn-primary" href="">Filter</a>
			</div>
			<div class="price-range-reset mbr-section-btn" buttons="0">
				<a class="btn btn-sm btn-secondary" href="">Show all</a>
			</div>
		</div>
		<div class="sidebar-block container sidebar-categories">
			<h4 class="sidebar-title mbr-fonts-style mbr-text display-7">Filter</h4>
			<div class="categories col-md-12">
				<div class="categories-titles">
					<!-- Filter -->
					<div
						class="mbr-gallery-filter mbr-shop-filter container gallery-filter-active">
						<ul buttons="0">
							<li class="mbr-gallery-filter-all active display-7">All</li>
							<li class="display-7">Awesome</li>
							<li class="display-7">Wonderful</li>
							<li class="display-7">Creative</li>
							<li class="display-7">Responsive</li>
							<li class="display-7">Animated</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<section class="mbr-gallery cid-qyXBYIkX4s" id="shop1-5f"
	data-rv-view="2434">
	<div>
		<div class="mbr-shop" sorting="" show-sidebar="" card-buttons="">
			<!-- Shop Gallery -->
			<div class=" col-md-12 sort-buttons clearfix" style="display: none;">
				<div class="sort-buttons" style="display: none;">
					<div class="filter-by-d mbr-section-btn item-button"
						style="text-align: center;">
						<a class="btn btn-sm btn-primary disableSortButton" href=""
							style="position: relative; display: inline-block;">Default
							sorting</a>
					</div>
					<div class="filter-by-pu mbr-section-btn">
						<a class="btn btn-sm btn-primary-outline disableSortButton"
							href="">Price: low to high</a>
					</div>
					<div class="filter-by-pd mbr-section-btn">
						<a class="btn btn-sm btn-primary-outline disableSortButton"
							href="">Price: high to low</a>
					</div>
				</div>
			</div>

			<!-- Lightbox -->
			<div class="shopItemsModal_wraper" style="z-index: 100;">
				<div class="shopItemsModalBg"></div>
				<div class="shopItemsModal row">
					<div class="col-md-6 image-modal"></div>
					<div class="col-md-6 text-modal"></div>
					<div class="closeModal">
						<div class="close-modal-wrapper"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</section>