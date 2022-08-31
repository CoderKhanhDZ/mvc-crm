<%@page import="mvcproject.java11.crm.urls.UrlsController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Left navbar-header -->
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse slimscrollsidebar">
		<ul class="nav" id="side-menu">
			<li style="padding: 10px 0 0;"><a
				href="<%=request.getContextPath() + UrlsController.URL_HOME %>" class="waves-effect"><i
					class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span
					class="hide-menu">Dashboard</span></a></li>

			<li><a
				href="<%=request.getContextPath() + UrlsController.URL_ACCOUNT_VIEW %>"
				class="waves-effect"><i class="fa fa-user fa-fw"
					aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a></li>

			<li><a
				href="<%=request.getContextPath() + UrlsController.URL_ROLE_VIEW %>"
				class="waves-effect"><i class="fa fa-modx fa-fw"
					aria-hidden="true"></i><span class="hide-menu">Quyền</span></a></li>

			<li><a
				href="<%=request.getContextPath() + UrlsController.URL_PROJECT_VIEW %>"
				class="waves-effect"><i class="fa fa-table fa-fw"
					aria-hidden="true"></i><span class="hide-menu">Dự án</span></a></li>

			<li><a
				href="<%=request.getContextPath() + UrlsController.URL_TASK_VIEW %>"
				class="waves-effect"><i class="fa fa-table fa-fw"
					aria-hidden="true"></i><span class="hide-menu">Công việc</span></a></li>
		</ul>
	</div>
</div>
<!-- Left navbar-header end -->