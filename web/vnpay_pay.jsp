<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Subject" %>
<%@ page import="dal.SubjectDAO" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Tạo mới đơn hàng</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .header {
                border-bottom: 1px solid #e5e5e5;
                margin-bottom: 20px;
            }
            .header h3 {
                margin: 0;
                padding: 10px 0;
                color: #6c757d;
            }
            h3 {
                color: #343a40;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input[type="number"],
            .form-group input[type="radio"] {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                border: 1px solid #ced4da;
                border-radius: 4px;
            }
            .form-check {
                margin-bottom: 10px;
            }
            .form-check input[type="radio"] {
                width: auto;
                margin-right: 10px;
            }
            .form-check label {
                display: inline-block;
                margin-bottom: 0;
            }
            .btn {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
            }
            .btn:hover {
                background-color: #0056b3;
            }
            .footer {
                text-align: center;
                padding: 20px 0;
                border-top: 1px solid #e5e5e5;
                margin-top: 20px;
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <body>

        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">VNPAY </h3>
            </div>
            <h3>New Order</h3>
            <%String raw_id=request.getParameter("subject_id");
            int id=0;
                    try {
            id=Integer.parseInt(raw_id);
        } catch (NumberFormatException e) {
        }
           SubjectDAO subjectDAO=new SubjectDAO();
           Subject subject=subjectDAO.getSubjectByID( id);%>
            <div class="table-responsive">
                <form action="vnpayajax?name=<%=subject.getSubjectName()%>"  method="post">        
                    <div class="form-group">
                        <label for="amount">Price:</label>
                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number" value="10000" />
                    </div>  
                    <div class="form-group">
                        <label for="amount">Description</label>
                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="description" type="text" value="Buy Subject: <%=subject.getSubjectName() %>" />
                    </div> 
                    <div class="form-group">
                        <h5>Language:</h5>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="language" name="language" value="vn" checked>
                            <label class="form-check-label" for="language">Tiếng việt</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="language" name="language" value="en">
                            <label class="form-check-label" for="language">Tiếng anh</label>
                        </div>
                    </div>
                    <button type="submit" class="btn">Purchase</button>
                </form>
            </div>
            <p>&nbsp;</p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>

        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>       
    </body>
</html>