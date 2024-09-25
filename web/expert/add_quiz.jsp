<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <jsp:include page="head.jsp" />
        <style>
            .container {
                max-width: 900px;
                margin: 0 auto;
                background-color: #f8f9fa;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            }

            .tab {
                margin-top: 20px;
            }

            .nav-tabs {
                border-bottom: 2px solid #dee2e6;
            }

            .nav-tabs .nav-link {
                border: none;
                background-color: #e9ecef;
                padding: 10px 20px;
                margin-right: 5px;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .nav-tabs .nav-link.active {
                background-color: #007bff;
                color: #fff;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
            }

            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 8px;
                border: 1px solid #ced4da;
                border-radius: 5px;
                background-color: #fff;
            }

            .tab-content {
                background-color: #fff;
                border: 1px solid #dee2e6;
                padding: 20px;
                border-radius: 5px;
            }

            .btn {
                background-color: #007bff;
                color: #fff;
                padding: 10px 15px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #0056b3;
            }

            .btn-secondary {
                background-color: #6c757d;
            }

            .form-inline {
                display: flex;
                align-items: center;
                gap: 10px;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp" />

                <main class="content">
                    <div class="container">
                        <div class="tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" href="#overview" data-bs-toggle="tab" role="tab">Overview</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#setting" data-bs-toggle="tab" role="tab">Setting</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <!-- Overview Tab -->
                                <div class="tab-pane active" id="overview" role="tabpanel">
                                    <h4>Quiz Details</h4>
                                    <form>
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input type="text" id="name" name="name" placeholder="Enter exam name">
                                        </div>
                                        <div class="form-group">
                                            <label for="subject">Subject</label>
                                            <select id="subject" name="subject_id" required>
                                                <c:set var="s" value="${requestScope.subject}"/>
                                                <option value="" disabled selected>Select an option</option>
                                                <c:forEach var="subject" items="${requestScope.listSubject}">
                                                    <option value="${subject.subjectId}" 
                                                            ${subject.subjectId == s.subjectId ? 'selected' : ''}>
                                                        ${subject.subjectName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="level">Exam Level</label>
                                            <select id="level" name="level_id" required>
                                                <option value="" disabled selected>Select an option</option>
                                                <c:forEach var="level" items="${requestScope.listLevel}">
                                                    <option value="${level.level_id}"> 
                                                        ${level.level_name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="duration">Duration (minutes)</label>
                                            <input type="number" id="duration" name="duration" value="50">
                                        </div>
                                        <div class="form-group">
                                            <label for="pass-rate">Pass Rate (%)</label>
                                            <input type="number" id="pass-rate" name="pass-rate" value="50">
                                        </div>
                                        <div class="form-group">
                                            <label for="quiz-type">Quiz Type</label>
                                            <select id="quiz-type" name="quiz-type">
                                                <option>Simulation</option>
                                                <!-- Add other types dynamically -->
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Description</label>
                                            <textarea id="description" name="description" placeholder="Enter description"></textarea>
                                        </div>
                                        <button type="submit" class="btn">Submit</button>
                                        <button type="button" class="btn btn-secondary">Back</button>
                                    </form>
                                </div>

                                <!-- Settings Tab -->
                                <div class="tab-pane" id="setting" role="tabpanel">
                                    <h4>Settings</h4>
                                    <form>
                                        <div class="form-group">
                                            <label for="total-questions">Total Number of Questions</label>
                                            <input type="number" id="total-questions" name="total-questions" value="50">
                                        </div>
                                         <div class="form-group">
                                            <label for="question-type">Question Type</label>
                                            <div class="form-inline">
                                                <input type="radio" id="theo-topic" name="question-type" value="topic">
                                                <label for="theo-topic">By Topic</label>
                                                <input type="radio" id="theo-group" name="question-type" value="group">
                                                <label for="theo-group">By Group</label>
                                                <input type="radio" id="theo-domain" name="question-type" value="domain">
                                                <label for="theo-domain">By Domain</label>
                                            </div>
                                        </div>
                                        Choose Questions by Group
                                        <br>
                                        <br>
                                        <!-- Group Selection 1 -->
                                        <div class="form-group d-flex align-items-center mb-3">
                                            <label for="group-selection-1" class="me-3">Group Selection 1</label>
                                            <select id="group-selection-1" name="group-selection-1" class="form-select me-2">
                                                <option>Group 1</option>
                                                <!-- Add group options dynamically -->
                                            </select>
                                            <input type="number" class="form-control me-2" id="number-of-questions-1" name="number-of-questions-1" placeholder="Questions" style="width: 100px;">
                                            <button type="button" class="btn btn-secondary">Delete</button>
                                        </div>

                                        <!-- Group Selection 2 -->
                                        <div class="form-group d-flex align-items-center mb-3">
                                            <label for="group-selection-2" class="me-3">Group Selection 2</label>
                                            <select id="group-selection-2" name="group-selection-2" class="form-select me-2">
                                                <option>Group 2</option>
                                                <!-- Add group options dynamically -->
                                            </select>
                                            <input type="number" class="form-control me-2" id="number-of-questions-2" name="number-of-questions-2" placeholder="Questions" style="width: 100px;">
                                            <button type="button" class="btn btn-secondary">Delete</button>
                                        </div>
                                        <button type="submit" class="btn">Save</button>
                                        <button type="button" class="btn btn-secondary">Back</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

                <jsp:include page="footer.jsp" />
            </div>
        </div>
        <jsp:include page="script.jsp" />
        
    </body>

</html>
