<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>


<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới Quyền</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
			
				<form class="form-horizontal form-material" action="<%= request.getContextPath() + UrlsController.URL_ROLE_EDIT %>" method="post">
					<input type="hidden" id="id" name="id" value="${roles.id}">
					<div class="form-group">
						<label class="col-md-12">Name Role</label>
						<div class="col-md-12">
							<input type="text" placeholder="role name" value="${roles.name}" id="name"
								class="form-control form-control-line" name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Description</label>
						<div class="col-md-12">
							<input type="text" placeholder="description" name="description" value="${roles.description}" id="description"
								class="form-control form-control-line">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success" >Update Role</button>
							<a href="<%=request.getContextPath() + UrlsController.URL_ROLE_VIEW %>" class="btn btn-primary">Quay lại</a>
						</div>
					</div>
				</form>
				
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>