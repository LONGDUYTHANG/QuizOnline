<%-- 
    Document   : quiz_handling
    Created on : Oct 3, 2024, 1:42:54 PM
    Author     : DELL-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="">
        <div>
            <form action="">
                <!-- Các câu hỏi -->
                <div class="quiz" id="quiz0">
                    <h2>Question 1: What is the capital of France?</h2>
                    <input type="radio" name="question1" value="a"> Berlin<br>
                    <input type="radio" name="question1" value="b"> Madrid<br>
                    <input type="radio" name="question1" value="c"> Paris<br>
                    <input type="radio" name="question1" value="d"> Rome<br>
                </div>

                <div class="quiz" id="quiz1">
                    <h2>Question 2: Which language is used for web development?</h2>
                    <input type="radio" name="question2" value="a"> Python<br>
                    <input type="radio" name="question2" value="b"> Java<br>
                    <input type="radio" name="question2" value="c"> C++<br>
                    <input type="radio" name="question2" value="d"> JavaScript<br>
                </div>

                <div class="quiz" id="quiz2">
                    <h2>Question 3: Who is the founder of Microsoft?</h2>
                    <input type="radio" name="question3" value="a"> Steve Jobs<br>
                    <input type="radio" name="question3" value="b"> Elon Musk<br>
                    <input type="radio" name="question3" value="c"> Bill Gates<br>
                    <input type="radio" name="question3" value="d"> Mark Zuckerberg<br>
                </div>
            </form>



            <!-- Nút điều hướng -->
            <button id="prev">Previous</button>
            <button id="next">Next</button>
            <button id="submit">Submit</button>

        </div>


    </body>

    <script>
        const totalQuestions = 3; // Tổng số câu hỏi
        let currentQuestion = 0; // Câu hỏi hiện tại bắt đầu từ 0
        let totalTime = 120; // Tổng thời gian (ví dụ: 120 giây)

        const timeEl = document.getElementById("time"); // Phần tử hiển thị thời gian

        const quizzes = document.querySelectorAll(".quiz");

// Hiển thị câu hỏi hiện tại
        function showQuestion(questionIndex) {
            for (let i = 0; i < totalQuestions; i++) {
                const quiz = quizzes[i];
                if (i === questionIndex) {
                    quiz.style.display = "block"; // Hiển thị câu hỏi hiện tại
                } else {
                    quiz.style.display = "none"; // Ẩn các câu hỏi khác
                }
            }
        }

// Cập nhật thời gian đếm ngược
        function updateTimer() {
            const minutes = Math.floor(totalTime / 60);
            const seconds = totalTime % 60;
            timeEl.textContent = `${minutes.toString().padStart(2, "0")}:${seconds
                                .toString()
                                .padStart(2, "0")}`;
                                    }

// Bắt đầu đếm ngược
                                    const timerInterval = setInterval(() => {
                                        totalTime--;
                                        updateTimer();

                                        if (totalTime <= 0) {
                                            clearInterval(timerInterval);
                                            // Hết thời gian, tự động nộp bài
                                            alert("Time is up! Quiz will be submitted.");
                                            submitQuiz(); // Gọi hàm nộp bài
                                        }
                                    }, 1000); // Cập nhật mỗi giây

// Chức năng cho nút "Previous"
                                    document.getElementById("prev").addEventListener("click", () => {
                                        if (currentQuestion > 0) {
                                            currentQuestion--; // Quay lại câu hỏi trước đó
                                            showQuestion(currentQuestion);
                                        }
                                    });

// Chức năng cho nút "Next"
                                    document.getElementById("next").addEventListener("click", () => {
                                        if (currentQuestion < totalQuestions - 1) {
                                            currentQuestion++; // Chuyển sang câu hỏi tiếp theo
                                            showQuestion(currentQuestion);
                                        }
                                    });

// Chức năng cho nút "Submit"
                                    document.getElementById("submit").addEventListener("click", () => {
                                        submitQuiz(); // Gọi hàm nộp bài
                                    });

// Hàm nộp bài
                                    function submitQuiz() {
                                        // Xử lý nộp bài hoặc tính điểm
                                        clearInterval(timerInterval); // Dừng đếm ngược khi nộp bài
                                        alert("Quiz submitted!");
                                    }

// Hiển thị câu hỏi đầu tiên khi tải trang
                                    showQuestion(currentQuestion);
                                    updateTimer(); // Khởi tạo hiển thị thời gian

    </script>
</html>
