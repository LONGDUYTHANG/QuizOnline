<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="">
        <!-- Thời gian đếm ngược -->
        <div id="time" style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 10px;">
            02:00
        </div>

        <div id="popup" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background-color:white; border:1px solid black; padding:20px; z-index:1001;">
            <h3>Thông báo</h3>
            <p>Thời gian đã hết! Bài kiểm tra sẽ được nộp.</p>
            <button onclick="submitQuiz()">OK</button>
        </div>
        <div id="popup_submit" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background-color:white; border:1px solid black; padding:20px; z-index:1001;">
            <h3>Thông báo</h3>
            <p>Do you want to submit</p>
            <button onclick="submitQuiz()">OK</button>
            <button onclick="closePopupSubmit()">Close</button>

        </div>

        <div id="overlay1" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.7); z-index:1000;"></div>
        <div id="overlay2" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.7); z-index:1000;"></div>
        <!-- Popup chọn câu hỏi -->
        <div id="questionSelectPopup" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background-color:white; border:1px solid black; padding:20px; z-index:1001;">
            <h3>Chọn câu hỏi</h3>
            <div id="filterButtons" style="display: flex; gap: 10px; margin-bottom: 10px;">
                <button class="filter-button" data-filter="all">Tất cả</button>
                <button class="filter-button" data-filter="answered">Đã làm</button>
                <button class="filter-button" data-filter="unanswered">Chưa làm</button>
                <button class="filter-button" data-filter="marked">Đã đánh dấu</button>
                <button class="submit-button" id="submit_button">Submit</button>
            </div>
            <div id="questionList" style="display: grid; grid-template-columns: repeat(10, 1fr); gap: 5px;"></div>
            <button onclick="closeQuestionSelectPopup()">Đóng</button>
        </div>




        <div id="selectQuestionButtonContainer">
            <button id="selectQuestionButton">Chọn Câu Hỏi</button>
        </div>

        <div>
            <form action="quiz_handling" method="post" id="submit_form">
                <c:forEach items="${sessionScope.questions}" var="qe" varStatus="status">
                    <div class="quiz" id="quiz${status.index}">
                        <h2>Question ${status.count} : ${qe.question_content}</h2>
                        <c:if test="${qe.media != null && (qe.media.endsWith('.jpg') || qe.media.endsWith('.jpeg') || qe.media.endsWith('.png') || qe.media.endsWith('.gif'))}">
                            <img src="${qe.media}" alt="alt" width="600" height="300"/> <br>
                        </c:if>

                        <c:forEach items="${qe.list_answer}" var="qe_a" varStatus="answerStatus">
                            <!-- Sử dụng phép toán với mã ASCII để lấy các chữ cái A, B, C, D -->
                            <input type="radio" name="question${status.count}" value="${qe_a.answer_detail}">
                            ${fn:substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ', answerStatus.index, answerStatus.index + 1)}: ${qe_a.answer_detail} <br>
                        </c:forEach>
                    </div>
                </c:forEach>



            </form>

            <!-- Nút điều hướng -->
            <button id="prev">Previous</button>
            <button id="next">Next</button>
            <button id="submit" onclick="showPopupSubmit()">Submit</button>

            <!-- Nút đánh dấu câu hỏi hiện tại -->
            <button id="markCurrentQuestion">Đánh Dấu Câu Hỏi Hiện Tại</button>
        </div>
        <div id="overlay3" style="display: none;position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.7); z-index:1000;"></div>
        <div id="popup_submitted" style="display: none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background-color:white; border:1px solid black; padding:20px; z-index:1001;">
            <h3>Thông báo</h3>
            <p>You have submitted</p>
            <button onclick="submitQuiz()">OK</button>

        </div>
        <!-- Mã HTML và JavaScript của bài kiểm tra -->



        <script>
            const totalQuestions = ${sessionScope.num_quest}; // Tổng số câu hỏi
            let currentQuestion = ${sessionScope.curr_quest}; // Câu hỏi hiện tại bắt đầu từ 0
            let totalTime = sessionStorage.getItem("totalTime") || ${sessionScope.duration}; // Tổng thời gian (ví dụ: 120 giây)
            let answered_questions = 0;

            const timeEl = document.getElementById("time"); // Phần tử hiển thị thời gian
            const quizzes = document.querySelectorAll(".quiz");
            const popup = document.getElementById("popup");
            const popup_submit = document.getElementById("popup_submit");
            const overlay1 = document.getElementById("overlay1");
            const overlay2 = document.getElementById("overlay2");
            const questionSelectPopup = document.getElementById("questionSelectPopup");
            const questionList = document.getElementById("questionList");
            const form = document.getElementById("submit_form");
            const submit_button = document.getElementById("submit_button");

            submit_button.addEventListener("click", () => {
                showPopupSubmit();
            });

            function showPopupSubmit() {
                questionSelectPopup.style.display = "none";
                popup_submit.style.display = "block"; // Hiển thị popup
                overlay2.style.display = "block"; // Hiển thị lớp phủ
            }

            // Hàm đóng popup thông báo
            function closePopupSubmit() {
                popup_submit.style.display = "none"; // Ẩn popup
                overlay2.style.display = "none"; // Ẩn lớp phủ
                showQuestionSelectPopup();
            }

            // Mảng trạng thái cho câu hỏi (unanswered, answered, marked)
            let questionStatus = Array(totalQuestions).fill('unanswered');
// Hàm đánh dấu câu hỏi hiện tại
            document.getElementById("markCurrentQuestion").addEventListener("click", () => {
                if (questionStatus[currentQuestion] !== 'marked')
                    questionStatus[currentQuestion] = 'marked';
                else
                    questionStatus[currentQuestion] = 'unanswered';
                saveQuestionStatus();

            });
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

            // Lưu câu hỏi hiện tại vào sessionStorage khi chuyển câu hỏi
            function saveCurrentQuestion() {
                sessionStorage.setItem("currentQuestion", currentQuestion);
            }

            // Lưu trạng thái câu hỏi vào sessionStorage khi chọn hoặc đánh dấu
            function saveQuestionStatus() {
                sessionStorage.setItem("questionStatus", JSON.stringify(questionStatus));
                for (let i = 0; i < totalQuestions; i++) {
                    const answers = document.getElementsByName('question' + (i + 1));
                    answers.forEach((answer, index) => {
                        if (answer.checked) {
                            sessionStorage.setItem('question' + (i + 1), index); // Lưu vị trí của đáp án được chọn
                        }
                    });
                }
            }
            // Hàm để khôi phục trạng thái các câu hỏi từ sessionStorage
            function restoreQuestionStatus() {
                const savedStatus = sessionStorage.getItem("questionStatus");
                if (savedStatus) {
                    questionStatus = JSON.parse(savedStatus);
                }

                for (let i = 0; i < totalQuestions; i++) {
                    const answers = document.getElementsByName('question' + (i + 1));
                    const savedAnswer = sessionStorage.getItem('question' + (i + 1));
                    if (savedAnswer !== null) {
                        answers[savedAnswer].checked = true; // Đặt lại trạng thái đã chọn cho câu hỏi
                    }
                }
            }

// Lưu trạng thái khi người dùng chọn câu trả lời hoặc đánh dấu câu hỏi
            document.querySelectorAll("input[type='radio']").forEach((input) => {
                input.addEventListener("change", saveQuestionStatus);
            });

// Tải trạng thái câu hỏi từ sessionStorage khi tải trang
            function loadQuestionStatus() {
                const savedStatus = sessionStorage.getItem("questionStatus");
                if (savedStatus) {
                    questionStatus = JSON.parse(savedStatus);
                    // Cập nhật hiển thị trạng thái màu của từng câu hỏi nếu cần
                    questionStatus.forEach((status, index) => {
                        const button = document.getElementById("question" + (index + 1));
                        if (button) {
                            if (status === 'answered') {
                                button.style.backgroundColor = "#d4edda";
                            } else if (status === 'marked') {
                                button.style.backgroundColor = "#fff3cd";
                            } else {
                                button.style.backgroundColor = "#f0f0f0";
                            }
                        }
                    });
                }
            }

            // Khôi phục câu hỏi hiện tại từ sessionStorage khi tải lại trang
            function restoreCurrentQuestion() {
                const savedCurrentQuestion = sessionStorage.getItem("currentQuestion");
                if (savedCurrentQuestion !== null) {
                    currentQuestion = parseInt(savedCurrentQuestion, 10); // Chuyển đổi sang số nguyên
                }
            }

// Cập nhật trạng thái của câu hỏi hiện tại và lưu lại
            function updateCurrentQuestionStatus() {
                const answers = document.getElementsByName('question' + (currentQuestion + 1));
                let answerSelected = false;
                answers.forEach(answer => {
                    if (answer.checked) {
                        answerSelected = true;
                    }
                });
                if (answerSelected) {
                    if (questionStatus[currentQuestion] !== 'marked') {
                        questionStatus[currentQuestion] = 'answered';
                        answered_questions++;
                    }
                }
                saveQuestionStatus(); // Lưu trạng thái sau khi cập nhật
            }

            // Cập nhật thời gian đếm ngược
            function updateTimer() {
                const minutes = Math.floor(totalTime / 60);
                const seconds = totalTime % 60;
                if (totalTime >= 0) {
                    timeEl.textContent =
                            (minutes < 10 ? "0" + minutes : minutes) + ":" +
                            (seconds < 10 ? "0" + seconds : seconds);
                }

                sessionStorage.setItem("totalTime", totalTime);
            }

            // Bắt đầu đếm ngược
            const timerInterval = setInterval(() => {
                totalTime--;
                updateTimer();
                if (totalTime <= 0) {
                    clearInterval(timerInterval);
                    // Hết thời gian, hiển thị popup
                    showPopup(); // Hiển thị popup
                    //submitQuiz(); // Gọi hàm nộp bài

                }

            }, 1000); // Cập nhật mỗi giây

            // Hàm hiển thị popup thông báo
            function showPopup() {
                questionSelectPopup.style.display = "none";
                popup.style.display = "block"; // Hiển thị popup
                overlay1.style.display = "block"; // Hiển thị lớp phủ
            }

            // Hàm đóng popup thông báo
            function closePopup() {
                popup.style.display = "none"; // Ẩn popup
                overlay1.style.display = "none"; // Ẩn lớp phủ
            }

            // Nút chọn câu hỏi
            document.getElementById("selectQuestionButton").addEventListener("click", () => {
                showQuestionSelectPopup(); // Mở popup với tất cả câu hỏi
            });

            // Thêm sự kiện cho các nút lọc
            document.querySelectorAll(".filter-button").forEach(button => {
                button.addEventListener("click", () => {
                    const filterValue = button.getAttribute("data-filter");
                    showQuestionSelectPopup(filterValue); // Gọi hàm hiển thị câu hỏi với bộ lọc được chọn
                });
            });

            // Cập nhật hàm showQuestionSelectPopup để luôn hiển thị tất cả câu hỏi
            function showQuestionSelectPopup(filterValue = "all") {
                questionList.innerHTML = ""; // Xóa danh sách câu hỏi cũ
                let num_list = 0;
                for (let i = 0; i < totalQuestions; i++) {
                    const button = document.createElement("button");
                    button.textContent = (i + 1);
                    button.style.width = "100%"; // Đặt chiều rộng cho nút
                    button.style.padding = "10px"; // Đặt khoảng cách bên trong
                    button.style.border = "1px solid #ccc"; // Đặt đường viền
                    button.style.cursor = "pointer"; // Hiển thị con trỏ khi di chuột qua

                    // Thiết lập màu sắc dựa trên trạng thái câu hỏi
                    if (questionStatus[i] === 'answered') {
                        button.style.backgroundColor = "#d4edda"; // Màu xanh nhạt cho câu hỏi đã làm
                    } else if (questionStatus[i] === 'marked') {
                        button.style.backgroundColor = "#fff3cd"; // Màu vàng cho câu hỏi đã đánh dấu
                    } else {
                        button.style.backgroundColor = "#f0f0f0"; // Màu xám cho câu hỏi chưa làm
                    }

                    button.onclick = () => {
                        updateCurrentQuestionStatus(); // Đánh dấu câu hỏi là đã làm
                        currentQuestion = i; // Chọn câu hỏi
                        saveCurrentQuestion(); // Lưu trạng thái câu hỏi hiện tại
                        showQuestion(currentQuestion); // Hiển thị câu hỏi được chọn
                        updateNavigationButtons();
                        closeQuestionSelectPopup(); // Đóng popup chọn câu hỏi
                    };

                    // Kiểm tra trạng thái dựa trên bộ lọc
                    if (
                            (filterValue === 'all') ||
                            (filterValue === 'answered' && questionStatus[i] === 'answered') ||
                            (filterValue === 'unanswered' && questionStatus[i] === 'unanswered') ||
                            (filterValue === 'marked' && questionStatus[i] === 'marked')
                            ) {
                        questionList.appendChild(button); // Thêm nút vào danh sách
                        num_list++;
                    }
                }
                if (num_list === 0) {
                    questionList.innerHTML = "Nothing";
                }

                questionSelectPopup.style.display = "block"; // Hiển thị popup chọn câu hỏi
                overlay1.style.display = "block"; // Hiển thị lớp phủ
            }

            // Hàm đóng popup chọn câu hỏi
            function closeQuestionSelectPopup() {
                questionSelectPopup.style.display = "none"; // Ẩn popup
                overlay1.style.display = "none"; // Ẩn lớp phủ
            }

            // Điều hướng câu hỏi
            const prevButton = document.getElementById("prev");
            const nextButton = document.getElementById("next");
            const submitButton = document.getElementById("submit");

            // Gọi hàm này khi người dùng chuyển đổi câu hỏi
            prevButton.addEventListener("click", () => {
                if (currentQuestion > 0) {
                    updateCurrentQuestionStatus(); // Cập nhật trạng thái trước khi quay lại
                    currentQuestion--; // Quay lại câu hỏi trước đó
                    showQuestion(currentQuestion);
                    saveCurrentQuestion(); // Lưu trạng thái câu hỏi hiện tại
                }
                updateNavigationButtons();
            });

            nextButton.addEventListener("click", () => {
                if (currentQuestion < totalQuestions - 1) {
                    updateCurrentQuestionStatus(); // Cập nhật trạng thái trước khi chuyển sang câu hỏi tiếp theo
                    currentQuestion++; // Chuyển sang câu hỏi tiếp theo
                    showQuestion(currentQuestion);
                    saveCurrentQuestion(); // Lưu trạng thái câu hỏi hiện tại
                }
                updateNavigationButtons();
            });

            // Hàm cập nhật trạng thái của các nút điều hướng và nút "Submit"
            function updateNavigationButtons() {
                prevButton.style.display = currentQuestion === 0 ? "none" : "inline";
                nextButton.style.display = currentQuestion === totalQuestions - 1 ? "none" : "inline";
                submitButton.style.display = currentQuestion === totalQuestions - 1 ? "inline" : "none";
            }

            // Khởi tạo trạng thái các nút khi tải trang
            updateNavigationButtons();

            // Chức năng cho nút "Submit"
//            document.getElementById("submit").addEventListener("click", () => {
//                updateCurrentQuestionStatus(); // Cập nhật trạng thái trước khi nộp bài
//                submitQuiz(); // Gọi hàm nộp bài
//                form.submit();
//            });

            // Hàm nộp bài
            function submitQuiz() {
                clearInterval(timerInterval);
                updateCurrentQuestionStatus(); // Cập nhật trạng thái trước khi nộp bài
                sessionStorage.setItem("isSubmitted", "true");

                form.submit();
            }

            function isSubmitted() {
                const isSub = sessionStorage.getItem("isSubmitted");
                if (isSub !== null && isSub === "true") {
                    clearInterval(timerInterval);
                    const popup_submitted = document.getElementById("popup_submitted");
                    const overlay3 = document.getElementById("overlay3");
                    popup_submitted.style.display = "block"; // Ẩn popup
                    overlay3.style.display = "block"; // Ẩn lớp phủ
                }
            }

            // Hiển thị câu hỏi đầu tiên khi tải trang
//            showQuestion(currentQuestion);
//            updateTimer(); // Khởi tạo hiển thị thời gian
// Gọi loadQuestionStatus khi tải trang để khôi phục trạng thái
            // Gọi hàm này khi tải lại trang để khôi phục câu hỏi hiện tại
            window.onload = () => {
                restoreCurrentQuestion(); // Khôi phục câu hỏi hiện tại
                restoreQuestionStatus(); // Khôi phục trạng thái câu hỏi
                showQuestion(currentQuestion); // Hiển thị câu hỏi hiện tại
                updateNavigationButtons();
                updateTimer(); // Khởi tạo hiển thị thời gian
                isSubmitted();
            };
        </script>

    </body>




</html>
