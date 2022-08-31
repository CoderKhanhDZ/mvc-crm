<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="aassets/plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/aassets/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="${pageContext.request.contextPath}/aassets/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- toast CSS -->
<link
	href="${pageContext.request.contextPath}/aassets/plugins/bower_components/toast-master/css/jquery.toast.css"
	rel="stylesheet">
<!-- morris CSS -->
<link href="${pageContext.request.contextPath}/aassets/plugins/bower_components/morrisjs/morris.css"
	rel="stylesheet">
<!-- animation CSS -->
<link href="${pageContext.request.contextPath}/aassets/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/aassets/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="${pageContext.request.contextPath}/aassets/css/colors/blue-dark.css" id="theme"
      rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/aassets/css/custom.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<div id="wrapper">

		<!-- HEADER  -->
		<jsp:include page="/views/layout/header.jsp" />

		<!-- SIDEBAR -->
		<jsp:include page="/views/layout/sidebar.jsp" />

		<!-- Page Content -->
		<div id="page-wrapper">

			<!--main body -->
			<dec:body />
		</div>
		<!-- Footer -->
		<jsp:include page="/views/layout/footer.jsp" />

	</div>
	<!-- /#page-wrapper -->

	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/aassets/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/aassets/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/aassets/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="${pageContext.request.contextPath}/aassets/js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="${pageContext.request.contextPath}/aassets/js/waves.js"></script>
	<!--Counter js -->
	<script
		src="${pageContext.request.contextPath}/aassets/plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="${pageContext.request.contextPath}/aassets/plugins/bower_components/counterup/jquery.counterup.min.js"></script>
	<!--Morris JavaScript -->
	<script src="${pageContext.request.contextPath}/aassets/plugins/bower_components/raphael/raphael-min.js"></script>
	<script src="${pageContext.request.contextPath}/aassets/plugins/bower_components/morrisjs/morris.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/aassets/js/custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/aassets/js/dashboard1.js"></script>
	<script
		src="${pageContext.request.contextPath}/aassets/plugins/bower_components/toast-master/js/jquery.toast.js"></script>
<script src="${pageContext.request.contextPath}/aassets/js/actionjs/account-edit.js"></script>
</body>
</html>