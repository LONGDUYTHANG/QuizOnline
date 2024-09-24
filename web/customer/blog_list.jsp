
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
    <body id="bg">

        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <!-- Header Top ==== -->
            <%@include file="header.html" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner2.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Blog List </h1>
                        </div>
                    </div>
                </div>
                <!-- contact area -->
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <!-- Left part start -->
                                <div class="col-lg-8">
                                    <!--thêm lay du lieu all post ? ?ây-->
                                    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <c:forEach items="${requestScope.post_list}" var="c">
                                    <div class="blog-post blog-md clearfix">
                                        <div class="ttr-post-media"> 
                                            <a href="blog_detail"><img src="assets/images/blog/grid/pic1.jpg" alt=""></a> 
                                        </div>
                                        <div class="ttr-post-info">

                                            <ul class="media-post">
                                                <li><a href="#"><i class="fa fa-calendar"></i>${c.created_date}</a></li>
                                                <li><a href="#"><i class="fa fa-user"></i>By William</a></li>
                                            </ul>
                                            <h5 class="post-title"><a href="blog_detail?blog_id=${c.blog_id}">${c.blog_summary}</a></h5>
                                            <p>${c.blog_content}</p>
                                            <div class="post-extra">
                                                <a href="blog_detail?blog_id=${c.blog_id}" class="btn-link">READ MORE</a>
                                                <a href="#" class="comments-bx"><i class="fa fa-comments-o"></i>05 Comment</a>
                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>

                                    <!-- Pagination start -->
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                                            <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                            <li class="active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- Pagination END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                <div class="col-lg-4 sticky-top">
                                    <aside class="side-bar sticky-top">
                                        <div class="widget">
                                            <h6 class="widget-title">Search</h6>
                                            <div class="search-bx style-1">
                                                <form role="search" method="post">
                                                    <div class="input-group">
                                                        <input name="text" class="form-control" placeholder="Enter your keywords..." type="text" id="output">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="fa fa-search text-primary"></button>
                                                        </span> 
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="widget widget_tag_cloud">
                                            <h6 class="widget-title">Tags</h6>
                                            <div class="tagcloud"> 
                                                <!--                                                                            l?y t?t c? categories -->										
                                                <button type="submit" id="myButton" onclick="search()" value="Design">Design</button>
                                            </div>
                                            <br>
                                            <div class="widget recent-posts-entry">
                                                <h6 class="widget-title">Hottest Posts</h6>
                                                <div class="widget-post-bx">
                                                    <!-- thêm danh sách các bài post hot nhat-->
                                                    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                                    <c:forEach items="${requestScope.post_list}" var="c">
                                                        <div class="widget-post clearfix">
                                                            <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic1.jpg" width="200" height="143" alt=""> </div>
                                                            <div class="ttr-post-info">
                                                                <div class="ttr-post-header">
                                                                    <h6 class="post-title"><a href="blog-details.html">${c.blog_summary}</a></h6>
                                                                </div>
                                                                <ul class="media-post">
                                                                    <li><a href="#"><i class="fa fa-calendar"></i>${c.created_date}</a></li>
                                                                    <li><a href="#"><i class="fa fa-comments-o"></i>15 Comment</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="widget widget-newslatter">
                                                <h6 class="widget-title">Newsletter</h6>
                                                <div class="news-box">
                                                    <p>Enter your e-mail and subscribe to our newsletter.</p>
                                                    <form class="subscription-form" action="http://educhamp.themetrades.com/demo/assets/script/mailchamp.php" method="post">
                                                        <div class="ajax-message"></div>
                                                        <div class="input-group">
                                                            <input name="dzEmail" required="required" type="email" class="form-control" placeholder="Your Email Address"/>
                                                            <button name="submit" value="Submit" type="submit" class="btn black radius-no">
                                                                <i class="fa fa-paper-plane-o"></i>
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>                                       
                                    </aside>
                                </div>
                                <!-- Side bar END -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="footer.html" %>
            <!-- Footer END ==== -->
            <!-- scroll top button -->
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
        <script>
                                                                                function search() {
                                                                                    var b = document.getElementById("myButton").value;
                                                                                    document.getElementById("output").value = b;

                                                                                }
        </script>
    </body>

</html>