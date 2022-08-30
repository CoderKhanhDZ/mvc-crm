<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>


<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chỉnh Sửa công việc</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">

				<form class="form-horizontal form-material"
					action="<%=request.getContextPath() + UrlsController.URL_TASK_EDIT %>"
					method="post">
					<div class="form-group">
						<input type="hidden" id="id" name="id" value="${task.id}">
						<label class="col-sm-12">Select Project</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="project_id"
								id="project_id">
								<option value="${task.project_id}">${task.project_name}</option>

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
								value="${task.name}" id="name"
								class="form-control form-control-line" name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Excutor</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="account_id"
								id="account_id">
								<option value="${task.account_id}">${task.account_name}</option>

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
								value="${task.start_date}" id="start_date"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">End Date</label>
						<div class="col-md-12">
							<input type="date" placeholder="YYYY-MM-DD" name="end_date"
								value="${task.end_date}" id="end_date"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Status</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="status_id"
								id="status_id">
								<option value="${task.status_id}">${task.status_name}</option>

								<c:forEach var="item" items="${status}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Update
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