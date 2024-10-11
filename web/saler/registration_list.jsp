
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
            <jsp:include page="sidebar.jsp" />
            
            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    <div class="container">

                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>Registration Time</th>
                                    <th>Subject</th>
                                    <th>Package</th>
                                    <th>Cost</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <c:forEach var="registration" items="${requestScope.registration_list}">
                                    <tr>
                                        <td>${requestScope.accountDAO.getEmailById(registration.account_id)}</td>
                                        <td>${registration.registration_time}</td>
                                        <td>${requestScope.subjectDAO.getSubjectByID(registration.subject_id).getSubjectName()}</td>
                                        <td>${requestScope.packageDAO.getPricePackageById(registration.package_id).getPackage_name()}</td>
                                        <td>${registration.cost}</td>
                                        
                                        <td style="width: 120px">
                                            <button type="button" class="btn btn-success" ><a href="registrationdetail?rid=${registration.registration_id}&aid=${registration.account_id}" style="color: white"><i class="align-middle me-2 fas fa-fw fa-edit"></i></a></button>
                                            <button type="button" class="btn btn-danger" ><a href="b.jsp" style="color: white"><i class="align-middle me-2 fas fa-fw fa-trash-alt"></a></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </table>
                    </div>
                </main>   
            </div>
        </div>
 <jsp:include page="script.jsp"/>
    </body>

</html>