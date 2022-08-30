<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Chi tiết công việc</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
			<ol class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li class="active">Blank Page</li>
			</ol>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- BEGIN THỐNG KÊ -->
	<div class="row">
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i data-icon="E" class="linea-icon linea-basic"></i>
						<h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-danger">20%</h3>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-danger" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 20%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
						<h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-megna">50%</h3>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-megna" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
						<h5 class="text-muted vb">HOÀN THÀNH</h5>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<h3 class="counter text-right m-t-15 text-primary">30%</h3>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 30%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- END THỐNG KÊ -->

	<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
	<div class="row">
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Chưa thực hiện</h3>
				<div class="message-center">

					<c:forEach var="task" items="${taskByProjectId }">
						<c:choose>
							<c:when test="${task.status_id == 3}">
								<div class="mail-contnet">
									<ul>
										<a href="#profile"><img src="#image-profile"
											style="width: 42px; height: 42px;"><span>
												${task.account_name }</span></a>
									</ul>
									<ul>
										<span>${task.name }</span>
										<span class="time">Bắt đầu: ${task.start_date}</span>
										<span class="time">Kết thúc: ${task.end_date}</span>
									</ul>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Đang thực hiện</h3>
				<div class="message-center">
				
					<c:forEach var="task" items="${taskByProjectId}">
						<c:choose>
							<c:when test="${task.status_id == 2 }">
								<div class="mail-contnet">
									<ul>
										<a href="#profile"><img src="#image-profile"
											style="width: 42px; height: 42px;"><span>
												${task.account_name }</span></a>
									</ul>
									<ul>
										<span>${task.name }</span>
										<span class="time">Bắt đầu: ${task.start_date}</span>
										<span class="time">Kết thúc: ${task.end_date}</span>
									</ul>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="white-box">
				<h3 class="box-title">Đã hoàn thành</h3>
				<div class="message-center">

					<c:forEach var="task" items="${taskByProjectId }">
						<c:choose>
							<c:when test="${task.status_id == 1 }">
								<div class="mail-contnet">
									<ul>
										<a href="#profile"><img src="#image-profile"
											style="width: 42px; height: 42px;"><span>
												${task.account_name }</span></a>
									</ul>
									<ul>
										<span>${task.name }</span>
										<span class="time">Bắt đầu: ${task.start_date}</span>
										<span class="time">Kết thúc: ${task.end_date}</span>
									</ul>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
	<!-- END DANH SÁCH CÔNG VIỆC -->
</div>