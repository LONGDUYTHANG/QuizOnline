<%-- 
    Document   : question_detail
    Created on : Sep 18, 2024, 11:30:32 PM
    Author     : FPT SHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .cancel-button {
                background-color: #ff4d4d;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 5px;
            }

            h1, h2 {
                color: #333;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                color: #333;
            }

            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-group textarea {
                height: 80px;
            }

            .buttons {
                display: flex;
                gap: 10px;
                margin-top: 10px;
            }

            .upload-btn, .preview-btn, .save-btn, .cancel-btn, .mark-btn, .edit-btn, .delete-btn {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 5px;
            }

            .upload-btn:hover, .preview-btn:hover, .save-btn:hover, .cancel-btn:hover, .mark-btn:hover, .edit-btn:hover, .delete-btn:hover {
                background-color: #0056b3;
            }

            .delete-btn {
                background-color: #ff4d4d;
            }

            .delete-btn:hover {
                background-color: #cc0000;
            }

            .cancel-btn {
                background-color: #f0ad4e;
            }

            .cancel-btn:hover {
                background-color: #ec971f;
            }

            .mark-btn {
                background-color: #5cb85c;
            }

            .mark-btn:hover {
                background-color: #4cae4c;
            }

            .answer {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 15px;
            }

            .answer textarea {
                width: calc(100% - 180px);
                height: 60px;
            }
            #message {
                display: none;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>


</html>





