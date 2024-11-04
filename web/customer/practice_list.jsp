<%-- 
    Document   : practice_list
    Created on : Nov 1, 2024, 1:09:47 PM
    Author     : DELL-PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"><!-- comment -->

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">
        <style>
            .border-head {
                border: 1px solid #cccccc;
                text-align: center
            }
            .border {
                height: 120px;
                display: flex;
                align-items: center;
                justify-content: center;
                font-weight: bold
            }
        </style>
    </head>
    <body id="bg">

        <%@include file="header.jsp" %>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Practice List</h1>
                        </div>
                    </div>
                </div> 
                <span>Filter by:</span>

                <div class="row" style="background-color: #7bdcb5; margin: 0 90px; font-size: 40px; font-weight: bold">
                    <div class="col-lg-2 text-content border-head">
                        Practice Title
                    </div>
                    <div class="col-lg-2 text-content border-head">
                        Exam Date
                    </div>
                    <div class="col-lg-2 text-content border-head">
                        Duration
                    </div>
                    <div class="col-lg-2 text-content border-head">
                        Correct Question
                    </div>
                    <div class="col-lg-2 text-content border-head">
                        Correct%
                    </div>
                    <div class="col-lg-2 text-content border-head">
                        Details
                    </div>

                </div>
                <div class="row" style="margin: 0 90px; font-size: 20px; ">
                    <c:forEach items="${sessionScope.list_prac}" var="lp">
                        <div class="col-lg-2 border" style="display: block">
                            ${lp.practice_name}
                            <span style="display: block">Type: ${lp.quiz.quiz_type.quiz_type_name}</span>
                        </div>
                        <div class="col-lg-2 border">
                            ${lp.created_date}
                        </div>
                        <div class="col-lg-2 border">
                            ${lp.practice_duration}s
                        </div>
                        <div class="col-lg-2 border">
                            ${lp.correct_questions}
                        </div>
                        <div class="col-lg-2 border">
                            
                            <div style="background-color: ${lp.correct_rate >= lp.quiz.passrate ? '#66ff66':'#ff6666'} ; border-radius: 8px; padding: 35px 0; color: white; width: 200px; height: 100px; text-align: center; align-items: center">
                                ${lp.correct_rate}%
                            </div>
                        </div>
                        <div class="col-lg-2 border">
                            <a href="quiz_review?practice_id=${lp.practice_id}">View</a>
                        </div>
                    </c:forEach>
                </div>
            </div> 
        </div> 

        <!-- Content END-->
        <!-- Footer ==== -->
        <%@include file="footer.html" %>
        <!-- Footer END ==== -->
        <button class="back-to-top fa fa-chevron-up" ></button>

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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">

        </script>
    </body>

</html>