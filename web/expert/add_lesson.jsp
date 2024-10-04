<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
                display: none; /* Hide by default */
            }
            
            .lesson-details {
                background-color: #fff;
                padding: 20px;
                margin: 20px auto;
                width: 90%;
                max-width: 600px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            h2 {
                color: #333;
            }

            .edit-btn, .delete-btn {
                padding: 10px 15px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .delete-btn {
                background-color: #dc3545;
            }

            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
                color: #333;
            }

            input, textarea, select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            textarea {
                resize: vertical;
            }

            iframe {
                width: 100%;
                height: 300px;
                margin-top: 15px;
            }

            button {
                cursor: pointer;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    <!-- Page 1: Lesson Topic -->
                    <div class="container lesson-topic" id="lesson-topic">
                        <div class="header">
                            <h2>Lesson Details</h2>
                            <div class="button-group">
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </div>
                        </div>
                        <form>
                            <div class="form-group">
                                <label for="type">Type</label>
                                <select id="type-selector-lesson">
                                    <option value="lesson">Lesson Topic</option>
                                    <option value="video">Video</option>
                                    <option value="quiz">Quiz</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" id="name" value="Course 1: Programming Concepts" disabled>
                            </div>

                            <div class="form-group">
                                <label for="summary">Summary</label>
                                <textarea id="summary" disabled>Sample text for summary</textarea>
                            </div>

                            <div class="form-group">
                                <label for="order">Order</label>
                                <input type="number" id="order" value="1">
                            </div>

                            <div class="form-group">
                                <label for="status">Status</label>
                                <select id="status">
                                    <option>Active</option>
                                    <option>Inactive</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <!-- Page 2: Video Type -->
                    <div class="container video" id="video">
                        <div class="header">
                            <h2>Lesson Details</h2>
                            <div class="button-group">
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </div>
                        </div>
                        <form>
                            <div class="form-group">
                                <label for="type">Type</label>
                                <select id="type-selector-video">
                                    <option value="lesson">Lesson Topic</option>
                                    <option value="video" selected>Video</option>
                                    <option value="quiz">Quiz</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" id="name" value="Advanced Programming Concepts" disabled>
                            </div>

                            <div class="form-group">
                                <label for="topic">Topic</label>
                                <input type="text" id="topic" value="Week 2: Object-Oriented Programming" disabled>
                            </div>

                            <div class="form-group">
                                <label for="summary">Summary</label>
                                <textarea id="summary" disabled>Sample text for summary</textarea>
                            </div>

                            <div class="form-group">
                                <label for="video-link">Video Link</label>
                                <input type="url" id="video-link" value="https://www.youtube.com/watch?v=example" disabled>
                                <iframe src="https://www.youtube.com/embed/example" frameborder="0" allowfullscreen></iframe>
                            </div>

                            <div class="form-group">
                                <label for="content">Content</label>
                                <textarea id="content">Add Lesson Details...</textarea>
                            </div>
                        </form>
                    </div>

                    <!-- Page 3: Quiz Type -->
                    <div class="container quiz" id="quiz">
                        <div class="header">
                            <h2>Lesson Details</h2>
                            <div class="button-group">
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </div>
                        </div>
                        <form>
                            <div class="form-group">
                                <label for="type">Type</label>
                                <select id="type-selector-quiz">
                                    <option value="lesson">Lesson Topic</option>
                                    <option value="video">Video</option>
                                    <option value="quiz" selected>Quiz</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" id="name" value="Quiz 1: Programming Concepts" disabled>
                            </div>

                            <div class="form-group">
                                <label for="quiz-type">Quiz Type</label>
                                <select id="quiz-type">
                                    <option>Multiple Choice</option>
                                    <option>True/False</option>
                                    <option>Short Answer</option>
                                    <option>Matching</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="summary">Summary</label>
                                <textarea id="summary" disabled>Review Chapter 1</textarea>
                            </div>

                            <div class="form-group">
                                <label for="selected-chapter">Selected Chapter</label>
                                <input type="text" id="selected-chapter" value="Review Chapter 1" disabled>
                            </div>
                        </form>
                    </div>
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>

        <!-- JavaScript for dynamic screen switching -->
        <script>
            function handleTypeChange(selectedType) {
    // Hide all containers
    document.getElementById('lesson-topic').style.display = 'none';
    document.getElementById('video').style.display = 'none';
    document.getElementById('quiz').style.display = 'none';

    // Show the selected container and set the dropdown value correctly
    if (selectedType === 'lesson') {
        document.getElementById('lesson-topic').style.display = 'block';
        document.getElementById('type-selector-lesson').value = 'lesson'; // Reset to Lesson
    } else if (selectedType === 'video') {
        document.getElementById('video').style.display = 'block';
        document.getElementById('type-selector-video').value = 'video'; // Reset to Video
    } else if (selectedType === 'quiz') {
        document.getElementById('quiz').style.display = 'block';
        document.getElementById('type-selector-quiz').value = 'quiz'; // Reset to Quiz
    }
}

// Add event listeners to dropdowns dynamically
document.querySelectorAll('select[id^="type-selector-"]').forEach(function(dropdown) {
    dropdown.addEventListener('change', function() {
        handleTypeChange(this.value);
    });
});

// Initialize the correct screen based on the selected value of any visible dropdown
window.addEventListener('load', function() {
    let selectedType = document.getElementById('type-selector-lesson').value || 'lesson';
    handleTypeChange(selectedType);
});

        </script>
    </body>
</html>
