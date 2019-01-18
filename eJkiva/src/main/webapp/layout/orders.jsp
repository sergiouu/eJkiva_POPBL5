<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
@charset "ISO-8859-1";

@media ( max-width : 991.98px) {
	img {
		position: relative;
		width: 100%;
		height: 100%;
	}
	div {
		text-align: center;
	}
	.card {
		margin: 20px;
	}
}
</style>

<div class="container justify-content-center">
	<c:choose>
		<c:when test="${not empty requestScope.orders}">
			<form:form method="post" action="orders.html">
				<div class="form-group">
					<p class="display-4">Your orders</p>
					<c:forEach items="${requestScope.orders}" var="order">
						<div class="col-lg-12">
							<div class="card">
								<div class="card-header" id="headingOne">
									<div class="form-group">
										<!-- button type="submit" id="register-submit" tabindex="4"
											class="btn btn-primary float-right display-7" name="order"
											value="${order.orderId}">More</button-->
										<p class="display-4">${order.dateOrder}</p>
										<p style="color: red;">
											<c:if test="${order.dateDelivered != null}">Delievered</c:if>
										</p>
									</div>
								</div>

							</div>
						</div>

					</c:forEach>
				</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<p>YOU DON'T HAVE ANY ORDER</p>
		</c:otherwise>

	</c:choose>
</div>

