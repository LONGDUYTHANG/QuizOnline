
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp" />

        <style>

            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: white;
                border-radius: 8px;
                padding: 20px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding-bottom: 15px;
            }

            .header h1 {
                font-size: 24px;
                color: #333;
            }

            .header nav button {
                margin-left: 10px;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn-blue {
                background-color: #007bff;
                color: white;
            }

            .btn-orange {
                background-color: #ff5722;
                color: white;
            }

            .tab-menu {
                display: flex;
                border-bottom: 2px solid #ccc;
                margin-bottom: 20px;
            }

            .tab-menu button {
                flex: 1;
                padding: 10px;
                background-color: white;
                border: none;
                cursor: pointer;
                font-size: 16px;
                color: #666;
                border-bottom: 4px solid transparent;
            }

            .tab-menu button.active {
                color: #5a2fc2;
                font-weight: bold;
                border-bottom-color: #5a2fc2;
            }

            .status {
                padding: 5px 10px;
                border-radius: 12px;
                font-size: 14px;
                text-align: center;
            }

            .status-published {
                background-color: #d4edda;
                color: #155724;
            }

            .status-unpublished {
                background-color: #f8d7da;
                color: #721c24;
            }
        </style>



    </head>
    <body >
        <div class="wrapper">

            <div class="main">

                <main class="content" >
                    <form action="updateregistration" method="post">
                        <div class="container">
                            <div class="row p-3" > 
                                <div class="col-4">
                                    <h3>Registration Details</h3>
                                </div>
                            </div>      
                            <div class="row p-3">
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Subject</p><!-- comment -->
                                    <input disabled type="text" value="${requestScope.subject}">
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Price Package</p>
                                    <select name="package" style="width: 195px;height: 28px" >
                                        <c:forEach items="${requestScope.package_list}" var="s">
                                            <option value="${s.package_id}" ${s.package_name eq requestScope.price_package?'selected':''}>${s.package_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Sale</p>
                                    <input type="text" value="${requestScope.sale}">
                                </div>
                            </div>

                            <div class="row p-3">
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">List Price</p>
                                    <input type="text" name="list_price" value="${requestScope.list_price}">
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Sale Price</p>
                                    <input type="text" name="sale_price" value="${requestScope.sale_price}">
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Status</p>
                                    <select name="status" style="width: 195px;height: 28px" >
                                        <c:forEach items="${requestScope.status_list}" var="s">
                                            <option value="${s.status_id}" ${s.status_name eq requestScope.status?'selected':''}>${s.status_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row p-3">
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Registration Time</p>
                                    <input type="text" value="${requestScope.registration_time}" disabled>
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Valid From</p>
                                    <input type="date" name="from" value="${requestScope.from}"><br>
                                </div>
                                <div class="col-4">
                                    <p class="form-label" style="font-weight: bolder">Valid To</p>
                                    <input type="date" name="to" value="${requestScope.to}"><br>
                                </div>
                            </div>

                        </div>
                        <div class="container" style="margin-top: 5%">
                            <div class="row p-3">
                                <div class="col-3">
                                    <p class="form-label" style="font-weight: bolder">Full Name</p>
                                    <input type="text" value="${requestScope.name}" disabled>
                                </div>
                                <div class="col-3">
                                    <p class="form-label" style="font-weight: bolder">Gender</p>
                                    <input type="text" value="${requestScope.gender == 1?'Male':'Female'}" disabled>
                                </div>
                                <div class="col-3">
                                    <p class="form-label" style="font-weight: bolder">Email</p>
                                    <input type="text" value="${requestScope.email}" disabled>
                                </div>
                                <div class="col-3">
                                    <p class="form-label" style="font-weight: bolder">Mobile</p>
                                    <input type="text" value="${requestScope.mobile}" disabled>
                                </div>
                            </div>
                            <div class="row p-3">
                                <div class="col-10">
                                    <p class="form-label" style="font-weight: bolder; width: 100px">Note</p>
                                    <input type="text" name="note" value="${requestScope.note}">
                                    <input type="text" name="registration_id" value="${requestScope.registration_id}" hidden>
                                </div>
                                <div class="col-2">
                                    <button class="btn btn-success" type="submit" ><i class="align-middle me-2 fas fa-fw fa-edit"></i></button>
                                    <button type="button" class="btn btn-danger" ><a href="registrationlist" style="color: white"><i class="fa-solid fa-house"></i></a></button>
                                </div>
                            </div>

                        </div>                           
                    </form>
                </main>   
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>

</html>