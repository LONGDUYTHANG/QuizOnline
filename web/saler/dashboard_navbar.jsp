<%-- 
    Document   : navbar
    Created on : Sep 18, 2024, 12:24:51 PM
    Author     : FPT SHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand navbar-light navbar-bg">
    <div style="margin-top: 10px">
        <h1 class="h3 mb-3"><strong>Statistic</strong> in</h1>
    </div>
     <div style="margin-left: 10px">
         <select name="cars" id="cars" onchange="changeId()" >
        <option value="volvo">This Week </option>
        <option value="saab">Months</option>
    </select>
     </div>
    <div class="navbar-collapse collapse">
        <ul class="navbar-nav navbar-align">
            <li class="nav-item">
                <a class="nav-icon js-fullscreen d-none d-lg-block" href="#">
                    <div class="position-relative">
                        <i class="align-middle" data-feather="maximize"></i>
                    </div>
                </a>
            </li>

        </ul>
    </div>
</nav>
