<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!DOCTYPE html>


<div class="container">
	<h2>Warehouse</h2>
	<p>Current state of the warehouse's workstations</p>
	<table class="table table-bordered table-sm">
		<thead>
			<tr>
				<th>Workstation</th>
				<th>Description</th>
				<th>State</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty requestScope.workstations}">
				<form:form method="post" action="warehouse.html">
					<div class="form-group">
						<div class="list-group list-group-flush" id="productsCart">
							<c:forEach items="${requestScope.workstations}" var="workstation">

								<tr>
									<td>${workstation.workstationId}</td>
									<td>${workstation.description}</td>
									<td><c:if test="${workstation.state == true}">Full</c:if><c:if test="${workstation.state == false}">Empty</c:if></td>
								</tr>

							</c:forEach>

						</div>
					</div>
				</form:form>
			</c:if>

		</tbody>
	</table>
</div>
