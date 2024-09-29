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
            #preview {
                margin-top: 20px;
                max-width: 100%;
                max-height: 300px;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="expert_sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    <!-- Success Message component -->
                    <div id="message" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <div class="alert-message">
                            <strong>Changes saved</strong> Question have been updated
                        </div>
                    </div>
                    <!-- Handle Showing success message -->
                    <script>
                        document.addEventListener('DOMContentLoaded', (event) => {
                            let showMessageSuccess = ${requestScope.showSuccessMessage};
                            let message = document.getElementById('message');

                            if (showMessageSuccess) {
                                // Show the message initially
                                message.style.display = 'block';
                                message.style.opacity = 1;

                                // Fade out after 0.5 seconds
                                setTimeout(() => {
                                    let opacity = 1;
                                    let interval = setInterval(() => {
                                        if (opacity <= 0) {
                                            clearInterval(interval);
                                            message.style.display = 'none';
                                        } else {
                                            opacity -= 0.05; // Adjust the decrement step for different speeds
                                            message.style.opacity = opacity;
                                        }
                                    }, 50); // Adjust interval timing as needed
                                }, 1000); // Show duration before starting fade-out
                            }
                        });
                    </script>
                    <!-- Header -->
                    <div class="header">
                        <h1>FPT University</h1>
                        <button class="btn btn-warning" type="submit" onclick="submitForm()">Save</button>
                    </div>

                    <!-- Submit the form when clicking on Save button -->
                    <script>
                        function submitForm() {
                            const requiredFields = [
                                document.getElementById('subject'),
                                document.getElementById('dimension'),
                                document.getElementById('lesson'),
                                document.getElementById('level'),
                                document.getElementById('status'),
                                document.getElementById('content'),
                                document.getElementById('explanation'),
                                document.getElementById('answer')
                            ];

                            // Check if all required fields are filled
                            for (let field of requiredFields) {
                                if (!field.value.trim()) {
                                    alert('Please fill in all required fields.');
                                    field.focus(); // Focus on the first empty field
                                    return; // Stop form submission
                                }
                            }

                            // Confirm before submission
                            if (confirm('Do you want to add new Question?')) {
                                document.getElementById('questiondetail').submit();
                            }
                        }
                    </script>


                    <!-- Question Details Section -->
                    <form id="questiondetail" action="addquestion" method="post" enctype="multipart/form-data">
                        <div class="container">
                            <div class="question-details">

                                <h2>Question details</h2>

                                <!-- Subject drop down -->
                                <div class="form-group">
                                    <label for="subject">Subject</label>
                                    <select id="subject" name="subject_id" required>
                                        <c:forEach var="subject" items="${requestScope.listSubject}">
                                            <option value="${subject.subjectId}" 
                                                    ${subject.subjectId == requestScope.subject_id ? 'selected' : ''}>
                                                ${subject.subjectName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Submit form directly when clicked on Subject drop down -->
                                <script>
                                    document.getElementById('subject').addEventListener('change', function () {
                                        var form = document.getElementById('questiondetail');
                                        form.action = 'question_detail_validation'; // Set the URL of the target servlet
                                        form.submit(); // Submit the form
                                    });
                                </script>

                                <!-- Dimension drop down -->
                                <div class="form-group">
                                    <label for="dimension">Dimension</label>
                                    <select id="dimension" name="dimension_id" required>
                                        <c:forEach var="dimension" items="${requestScope.listDimension}">
                                            <option value="${dimension.dimension_id}"> 
                                                ${dimension.dimension_name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Lesson drop down -->
                                <div class="form-group">
                                    <label for="lesson">Lesson</label>
                                    <select id="lesson" name="lesson_topic_id" required>
                                        <c:forEach var="lesson" items="${requestScope.listLesson_Topic}">
                                            <option value="${lesson.lesson_topic_id}"> 
                                                ${lesson.lesson_topic_name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Level drop down -->
                                <div class="form-group">
                                    <label for="level">Level</label>
                                    <select id="level" name="level_id" required>
                                        <c:forEach var="level" items="${requestScope.listLevel}">
                                            <option value="${level.level_id}" ${level.level_id == requestScope.level_id ? 'selected' : ''}> 
                                                ${level.level_name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Status drop down -->
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <select id="status" name="status"  class="form-control" required>
                                        <option value="1" ${requestScope.status == 1 ? 'selected' : ''}>Active</option>
                                        <option value="0" ${requestScope.status == 0 ? 'selected' : ''}>Inactive</option>
                                    </select>
                                </div>

                                <!-- Question Content -->
                                <div class="form-group">
                                    <label for="content">Content</label>
                                    <textarea id="content" name="content" placeholder="Enter content" required>${requestScope.content}</textarea>
                                </div>

                                <!-- Explanation -->
                                <div class="form-group">
                                    <label for="explanation">Explanation</label>
                                    <textarea id="explanation" name="explanation" placeholder="Enter your explanation" required>${requestScope.explanation}</textarea>
                                </div>

                                <!-- File Upload -->
                                <div class="form-group">
                                    <label for="file_upload">Enter link or upload file</label>

                                    <input type="file" id="fileInput" name="media" accept="video/*,audio/*,image/*" style="display: none;" onchange="showPreview(event)">
                                    
                                    <div class="buttons">
                                        <!-- Upload button triggers file input -->
                                        <button type="button" class="btn btn-warning" onclick="document.getElementById('fileInput').click();" style="margin-bottom: 20px">Upload file</button>
                                    </div>
                                    <div id="preview-container">
                                        <img id="preview-image" style="display: none; max-width: 100%;">
                                        <video id="preview-video" controls style="display: none; max-width: 100%;"></video>
                                        <audio id="preview-audio" controls style="display: none;"></audio>
                                    </div>
                                </div>

                                <script>
                                    // JavaScript to show the preview of the selected file
                                    function showPreview(event) {
                                        const file = event.target.files[0];
                                        const imagePreview = document.getElementById('preview-image');
                                        const videoPreview = document.getElementById('preview-video');
                                        const audioPreview = document.getElementById('preview-audio');

                                        // Reset all previews
                                        imagePreview.style.display = 'none';
                                        videoPreview.style.display = 'none';
                                        audioPreview.style.display = 'none';

                                        if (file) {
                                            const fileType = file.type;

                                            if (fileType.startsWith('image/')) {
                                                // Preview image
                                                const reader = new FileReader();
                                                reader.onload = function (e) {
                                                    imagePreview.src = e.target.result;
                                                    imagePreview.style.display = 'block';
                                                };
                                                reader.readAsDataURL(file);
                                            } else if (fileType.startsWith('video/')) {
                                                // Preview video
                                                const videoURL = URL.createObjectURL(file);
                                                videoPreview.src = videoURL;
                                                videoPreview.style.display = 'block';
                                            } else if (fileType.startsWith('audio/')) {
                                                // Preview audio
                                                const audioURL = URL.createObjectURL(file);
                                                audioPreview.src = audioURL;
                                                audioPreview.style.display = 'block';
                                            } else {
                                                alert('Please select an image, video, or audio file.');
                                            }
                                        }
                                    }
                                </script>
                            </div>
                        </div>

                        <!-- Answer Details Section -->
                        <div class="container" style="margin-top: 10px">
                            <div class="answer-details">
                                <h2>Answer details</h2>

                                <div class="buttons">
                                    <button class="btn btn-success" id="add-btn">Add <i class="align-middle me-2 fas fa-fw fa-plus-circle"></i></button>
                                </div>

                                <div class="form-group">
                                    <label for="answer">Answer</label>
                                    <!-- Check mark icon and input -->
                                    <span class="correct-icon" style="display:none;">✔️</span>
                                    <input type="hidden" name="is_correct" class="is-correct" value="false">

                                    <!-- Answer content -->
                                    <input style="margin-bottom: 5px" type="text" id="answer" name="answer" required>
                                    <button type="button" class="btn btn-danger" onclick="deleteAnswer(this)">Delete</button>
                                    <button type="button" class="btn btn-success" onclick="markAsCorrect(this)">Mark as correct</button>
                                </div>

                                <!-- Handle Check Mark -->
                                <script>
                                    function markAsCorrect(button) {
                                        const parentDiv = button.closest('.form-group');
                                        const icon = parentDiv.querySelector('.correct-icon');
                                        const hiddenInput = parentDiv.querySelector('.is-correct');

                                        if (hiddenInput.value === 'false') {
                                            hiddenInput.value = 'true'; // Mark as correct
                                            icon.style.display = 'inline'; // Show the icon
                                        } else {
                                            hiddenInput.value = 'false'; // Unmark
                                            icon.style.display = 'none'; // Hide the icon
                                        }
                                    }
                                </script>

                                <!-- Generate new Answer each time user click on Add button -->
                                <script>
                                    document.getElementById('add-btn').addEventListener('click', function () {
                                        const newFormGroup = document.createElement('div');
                                        newFormGroup.classList.add('form-group');
                                        newFormGroup.innerHTML = newFormGroup.innerHTML = `
                                        <label for="answer">Answer</label>
                                        <span class="correct-icon" style="display:none;">✔️</span>
                                        <input type="hidden" name="is_correct" class="is-correct" value="false">
                                        <input style="margin-bottom: 5px" type="text" id="answer" name="answer" required>
                                        <button type="button" class="btn btn-danger" onclick="deleteAnswer(this)">Delete</button>
                                        <button type="button" class="btn btn-success" onclick="markAsCorrect(this)">Mark as correct</button>
                                    `;
                                        document.querySelector('.answer-details').appendChild(newFormGroup);
                                    });
                                </script>

                                <!-- Delete Answer each time user click on delete button -->
                                <script>
                                    function deleteAnswer(button) {
                                        const formGroup = button.closest('.form-group');
                                        if (formGroup) {
                                            formGroup.remove(); // Removes the form group from the DOM
                                        }
                                    }
                                </script>
                            </div>
                        </div>
                    </form>
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
        <jsp:include page="script.jsp"/>
    </body>


</html>





