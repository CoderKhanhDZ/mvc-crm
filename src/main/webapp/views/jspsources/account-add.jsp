<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>


<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">

			<div class="white-box">
				<c:if test="${!empty message}">
					<div class="alert alert-danger" role="alert">${message}</div>
				</c:if>
				<form class="form-horizontal form-material"
					action="<%=request.getContextPath() + UrlsController.URL_ACCOUNT_ADD%>"
					method="post">
					<div class="form-group">
						<label class="col-md-12">Full Name</label>
						<div class="col-md-12">
							<input type="text" placeholder="Johnathan Doe"
								class="form-control form-control-line" name="fullname" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Email</label>
						<div class="col-md-12">
							<input type="email" placeholder="johnathan@admin.com"
								class="form-control form-control-line" name="email" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Password</label>
						<div class="col-md-12">
							<input type="password" name="password"
								class="form-control form-control-line" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Phone No</label>
						<div class="col-md-12">
							<input type="text" placeholder="123 456 7890" name="phone"
								class="form-control form-control-line" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Address</label>
						<div class="col-md-12">
							<input type="text"
								placeholder="81, 6th street, Tan Phong Ward, district 7, Ho Chi Minh city"
								name="address" class="form-control form-control-line" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Role</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="role_id">
								<c:forEach var="item" items="${role}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add User</button>
							<a
								href="<%=request.getContextPath() + UrlsController.URL_ACCOUNT_VIEW%>"
								class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>