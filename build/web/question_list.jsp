
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

            header h1 {
                font-size: 24px;
                color: #4a4a4a;
            }

            header p {
                color: #999;
                margin-top: 5px;
            }

            nav {
                margin: 20px 0;
            }

            .nav-btn {
                background-color: #f0f0f0;
                border: none;
                padding: 10px 15px;
                margin-right: 10px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .nav-btn.active,
            .nav-btn:hover {
                background-color: #4a4a4a;
                color: white;
            }

            .search-section {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .search-bar {
                width: 60%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            .button-group button {
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-left: 10px;
            }

            .import-btn {
                background-color: #007bff;
                color: white;
            }

            .new-question-btn {
                background-color: #ff5722;
                color: white;
            }

            .question-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            .question-table th,
            .question-table td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .question-table th {
                background-color: #f9f9f9;
            }

            .status {
                padding: 5px 10px;
                border-radius: 12px;
                font-size: 12px;
            }

            .unpublished {
                background-color: #f44336;
                color: white;
            }

            .published {
                background-color: #4caf50;
                color: white;
            }

            .option-btn {
                background: none;
                border: none;
                font-size: 16px;
                cursor: pointer;
            }

            .pagination {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }

            .pagination-controls {
                display: flex;
                align-items: center;
            }

            .pagination-btn {
                padding: 5px 10px;
                border: 1px solid #ddd;
                background-color: #f0f0f0;
                cursor: pointer;
                margin: 0 5px;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    <div class="container">
                        <header>
                            <h1>Question List</h1>
                            <p>View and manage question</p>
                        </header>

                        <nav>
                            <button class="nav-btn active">All Question</button>
                            <button class="nav-btn">Draft</button>
                            <button class="nav-btn">Published</button>
                            <button class="nav-btn">Unpublished</button>
                        </nav>

                        <section class="search-section">
                            <h2>Question from Subject [Subject name]</h2>
                            <input type="text" placeholder="Search for subject name here" class="search-bar">
                            <div class="button-group">
                                <button class="import-btn">Question Import</button>
                                <button class="new-question-btn">New Question</button>
                            </div>
                        </section>

                        <table class="question-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Content</th>
                                    <th>Subject</th>
                                    <th>Level</th>
                                    <th>Status</th>
                                    <th>Option</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>What is a programming language?</td>
                                    <td>Subject name [Lesson] Name [Dimension name]</td>
                                    <td>100</td>
                                    <td><span class="status unpublished">Unpublished</span></td>
                                    <td><button class="option-btn">...</button></td>
                                </tr>
                                <!-- More rows as needed -->
                            </tbody>
                        </table>

                        <footer class="pagination">
                            <p>1 - 5 of 56</p>
                            <div class="pagination-controls">
                                <button class="pagination-btn">←</button>
                                <span>1</span>
                                <button class="pagination-btn">→</button>
                            </div>
                        </footer>
                    </div>
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>

</html>