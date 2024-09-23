
<!DOCTYPE html>
<html lang="en">


    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <!-- Header Top ==== -->
            <%@include file="header.html" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">All Courses</h1>
                        </div>
                    </div>
                </div>

                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <!<!-- thêm account -->
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <c:if test="${not empty requestScope.myAccount}">
                                        <div class="widget widget_archive">
                                            <h5 class="widget-title style-1">${requestScope.myAccount.full_name}</h5>
                                            <ul>
                                                <li class="active"><a href="registerd_subject_list.jsp">My Registered Subjects</a></li>
                                                <li><a href="subject_list">All Courses</a></li>
                                                <li><a href="profile.jsp">Profile</a></li>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty requestScope.myAccount}">
                                        <div class="widget widget_archive">
                                            <h5 class="widget-title style-1">Guest</h5>
                                            <ul>
                                                <li><a href="login.jsp">Login</a></li>
                                                <li><a href="subject_list">All Courses</a></li>
                                            </ul>
                                        </div>
                                    </c:if>
                                </div>


                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <div class="row">
                                        <div class="col-md-6 col-lg-4 col-sm-6 m-b30">
                                            <!-- thêm danh sách các subject-->
                                            <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                            <c:forEach items="${requestScope.subject_list}" var="c">
                                                <div class="cours-bx">
                                                    <div class="action-box">
                                                        <img src="assets/images/courses/pic1.jpg" alt="">
                                                        <a href="subject_details" class="btn">Read More</a>
                                                    </div>
                                                    <div class="info-bx text-center">
                                                        <h5><a href="subject_details.jsp">${c.description}</a></h5>
                                                        <span>${c.tagline}</span>
                                                    </div>
                                                    <div class="cours-more-info">
                                                        <div class="review">
                                                            <span>3 Review</span>
                                                            <ul class="cours-star">
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                <li><i class="fa fa-star"></i></li>
                                                                <li><i class="fa fa-star"></i></li>
                                                            </ul>
                                                        </div>
                                                        <div class="price">
                                                            <del>$190</del>
                                                            <h5>$120</h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <!--                                        list subject-->

                                        <div class="col-lg-12 m-b20">
                                            <div class="pagination-bx rounded-sm gray clearfix">
                                                <ul class="pagination">
                                                    <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                                    <li class="active"><a href="#">1</a></li>
                                                    <li><a href="#">2</a></li>
                                                    <li><a href="#">3</a></li>
                                                    <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->

            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="footer.html" %>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>
        <!-- External JavaScripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src='assets/vendors/switcher/switcher.js'></script>
    </body>

</html>
