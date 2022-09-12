<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 8/31/2022
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href='${pageContext.request.contextPath}/aassets/plugins/images/favicon.png">'>
    <title>403 - Forbidden</title>
    <!-- Bootstrap Core CSS -->
    <link href='${pageContext.request.contextPath}/aassets/bootstrap/dist/css/bootstrap.min.css' rel="stylesheet">
    <!-- animation CSS -->
    <link href='${pageContext.request.contextPath}/aassets/css/animate.css' rel="stylesheet">
    <!-- Custom CSS -->
    <link href='${pageContext.request.contextPath}/aassets/css/style.css' rel="stylesheet">
    <!-- color CSS -->
    <link href='${pageContext.request.contextPath}/aassets/css/colors/blue.css' id="theme" rel="stylesheet">
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
    <section id="wrapper" class="error-page">
        <div class="error-box">
            <div class="error-body text-center">
                <h1>404</h1>
                <h3 class="text-uppercase">Trang này không tồn tại!</h3>
                <p class="text-muted m-t-30 m-b-30">YOU SEEM TO BE TRYING TO FIND HIS WAY HOME</p>
                <a href='<%= request.getContextPath() + UrlsController.URL_HOME %>' class="btn btn-info btn-rounded waves-effect waves-light m-b-40">Về trang chủ</a> </div>
            <footer class="footer text-center">2018 © Pixel Admin.</footer>
        </div>
    </section>
    <!-- jQuery -->
    <script src='${pageContext.request.contextPath}/aassets/plugins/bower_components/jquery/dist/jquery.min.js'></script>
    <!-- Bootstrap Core JavaScript -->
    <script src='${pageContext.request.contextPath}/aassets/bootstrap/dist/js/bootstrap.min.js'></script>
    <script type="text/javascript">
    $(function() {
        $(".preloader").fadeOut();
    });
    </script>
</body>

</html>
