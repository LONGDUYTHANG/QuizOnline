
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp" />

        <!-- DataTables CSS -->
        <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">

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

        <!-- jQuery (Required for DataTables) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#questionTable').DataTable({
                    "paging": true,
                    "searching": true,
                    "ordering": true,
                    "info": true
                });
            });
        </script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />
            
            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">

                    <div class="header">
                        <h1>Quiz List</h1>
                        <nav>
                            <button onclick="window.location.href='addlesson?subjectId=${requestScope.subject_id}'" class="btn btn-orange">New Lesson <i class="align-middle me-2 fas fa-fw fa-plus-circle"></i></button>
                        </nav>
                    </div>
                    <div class="container">

                        <table id="questionTable" class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Lesson ID</th>
                                    <th>Lesson</th>
                                    <th>Order</th>
                                    <th>Type</th>
                                    <th>Option</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="lesson" items="${requestScope.listLesson}">
                                    <tr>
                                        <td>${lesson.lesson_id}</td>
                                        <td>${lesson.lesson_name}</td>
                                        <td>${lesson.lesson_order}</td>
                                        <td>${lesson.getLesson_Type(requestScope.dao).getLesson_type_name()}</td>
                                        <td style="width: 120px">
                                            <button type="button" class="btn btn-success"><i class="align-middle me-2 fas fa-fw fa-edit"></i></button>
                                            <button type="button" class="btn btn-danger"><i class="align-middle me-2 fas fa-fw fa-trash-alt"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <!-- More rows can be added here -->
                            </tbody>
                        </table>
                    </div>
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>

</html>