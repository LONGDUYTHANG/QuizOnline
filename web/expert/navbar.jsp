<%-- 
    Document   : navbar
    Created on : Sep 18, 2024, 12:24:51 PM
    Author     : FPT SHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand navbar-light navbar-bg">
    <a class="sidebar-toggle js-sidebar-toggle">
        <i class="hamburger align-self-center"></i>
    </a>

    <ul class="navbar-nav d-none d-lg-flex">
        <li class="nav-item px-2 dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="megaDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">
                Feature
            </a>
            <div class="dropdown-menu dropdown-menu-start dropdown-mega" aria-labelledby="megaDropdown">
                <div class="d-md-flex align-items-start justify-content-start">
                    <div class="dropdown-mega-list">
                        <div class="dropdown-header">List Feature</div>
                        <a class="dropdown-item" href="#">Subject List</a>
                        <a class="dropdown-item" href="#">Lesson List</a>
                        <a class="dropdown-item" href="#">Quiz List</a>
                        <a class="dropdown-item" href="questionlist">Question List</a>
                    </div>
                    <div class="dropdown-mega-list">
                        <div class="dropdown-header">Add Feature</div>
                        <a class="dropdown-item" href="#">New Subject</a>
                        <a class="dropdown-item" href="#">New Lesson</a>
                        <a class="dropdown-item" href="#">New Quiz</a>
                        <a class="dropdown-item" href="question_detail_validation">New Question</a>
                    </div>
                </div>
            </div>
        </li>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="resourcesDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">
                Common
            </a>
            <div class="dropdown-menu" aria-labelledby="resourcesDropdown">
                <a class="dropdown-item" href="#" target="_blank"><i class="align-middle me-1" data-feather="sliders"></i>
                    DashBoard</a>
            </div>
        </li>
    </ul>

    <div class="navbar-collapse collapse">
        <ul class="navbar-nav navbar-align">
            <li class="nav-item">
                <a class="nav-icon js-fullscreen d-none d-lg-block" href="#">
                    <div class="position-relative">
                        <i class="align-middle" data-feather="maximize"></i>
                    </div>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-icon pe-md-0 dropdown-toggle" href="#" data-bs-toggle="dropdown">
                    <img src="${sessionScope.user.avatar}" class="avatar img-fluid rounded" alt="" />
                </a>
            </li>
        </ul>
    </div>
</nav>
