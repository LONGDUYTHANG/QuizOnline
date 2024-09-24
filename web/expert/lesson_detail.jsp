<%-- 
    Document   : lesson_detail
    Created on : Sep 18, 2024, 6:08:07 PM
    Author     : FPT SHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp" />
        <style>
           
            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input,
            .form-group select {
                width: 100%;
                padding: 8px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .form-group input[disabled],
            .form-group select[disabled] {
                background-color: #f9f9f9;
            }
            .form-actions {
                display: flex;
                justify-content: space-between;
            }
            .btn {
                padding: 10px 20px;
                font-size: 16px;
                color: #fff;
                background-color: #f05a5a;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .btn.edit {
                background-color: #f5b74c;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    <div class="container">
                        <h1>Lesson Details</h1>

                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" id="name" value="Chapter 1: Programming Concepts" disabled>
                        </div>

                        <div class="form-group">
                            <label for="summary">Summary</label>
                            <input type="text" id="summary" value="this is a test topic for demo purpose" disabled>
                        </div>

                        <div class="form-group">
                            <label for="type">Type</label>
                            <select id="type" disabled>
                                <option value="topic" selected>Topic</option>
                                <!-- Other options can be added here -->
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="order">Order</label>
                            <input type="number" id="order" value="2" disabled>
                        </div>

                        <div class="form-group">
                            <label for="status">Status</label>
                            <select id="status" disabled>
                                <option value="inactive" selected>Inactive</option>
                                <!-- Other options can be added here -->
                            </select>
                        </div>

                        <div class="form-actions">
                            <button class="btn edit">Edit</button>
                            <button class="btn go-back">Go Back</button>
                        </div>
                    </div> 
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>

</html>

