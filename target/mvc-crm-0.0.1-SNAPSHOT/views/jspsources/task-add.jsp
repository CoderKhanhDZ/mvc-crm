<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>


<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới công việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<c:if test="${!empty message}">
				<div class="alert alert-success" role="alert">${message}</div>
			</c:if>
			<div class="white-box">
				<form class="form-horizontal form-material"
					action="<%=request.getContextPath() + UrlsController.URL_TASK_ADD%>"
					method="post">
					<div class="form-group">
						<label class="col-sm-12">Select Project</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="project_id">
								<c:forEach var="item" items="${project}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Name Task</label>
						<div class="col-md-12">
							<input type="text" placeholder="working thing ?"
								class="form-control form-control-line" name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Excutor</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="account_id">
								<c:forEach var="item" items="${account}">
									<option value="${item.id}">${item.fullname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Start Date</label>
						<div class="col-md-12">
							<input type="date" placeholder="YYYY-MM-DD" name="start_date"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">End Date</label>
						<div class="col-md-12">
							<input type="date" placeholder="YYYY-MM-DD" name="end_date"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Create
								Task</button>
							<a
								href="<%=request.getContextPath() + UrlsController.URL_TASK_VIEW%>"
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