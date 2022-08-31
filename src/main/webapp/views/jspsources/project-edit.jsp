<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>


<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chỉnh Sửa Dự Án</h4>
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
					action="<%=request.getContextPath() + UrlsController.URL_PROJECT_EDIT%>"
					method="post">
					<input type="hidden" id="id" name="id" value="${projects.id}">
					<div class="form-group">
						<label class="col-md-12">Name Project</label>
						<div class="col-md-12">
							<input type="text" placeholder="project name"
								value="${projects.name}" id="name"
								class="form-control form-control-line" name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Start Date</label>
						<div class="col-md-12">
							<input type="date" placeholder="YYYY-MM-DD" name="start_date"
								value="${projects.start_date}" id="start_date"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">End Date</label>
						<div class="col-md-12">
							<input type="date" placeholder="YYYY-MM-DD" name="end_date"
								value="${projects.end_date}" id="end_date"
								class="form-control form-control-line">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Update
								Project</button>
							<a
								href="<%=request.getContextPath() + UrlsController.URL_PROJECT_VIEW%>"
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