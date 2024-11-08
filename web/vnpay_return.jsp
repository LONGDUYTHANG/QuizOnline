<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Payment Result</title>
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
            .form-group .value {
                display: block;
                padding: 8px;
                background-color: #f1f1f1;
                border: 1px solid #ced4da;
                border-radius: 4px;
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
                margin-top: 20px;
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
    </head>

    <body>

        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">PAYMENT RESULT</h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label>Transaction ID:</label>
                    <span class="value"><%=request.getParameter("vnp_TxnRef")%></span>
                </div>    
                <div class="form-group">
                    <label>Amount:</label>
                    <span class="value"><%=request.getParameter("vnp_Amount")%></span>
                </div>  
                <div class="form-group">
                    <label>Order Description:</label>
                    <span class="value"><%=request.getParameter("vnp_OrderInfo")%></span>
                </div> 
                <div class="form-group">
                    <label>Response Code:</label>
                    <span class="value"><%=request.getParameter("vnp_ResponseCode")%></span>
                </div> 
                <div class="form-group">
                    <label>Transaction No at VNPAY-QR:</label>
                    <span class="value"><%=request.getParameter("vnp_TransactionNo")%></span>
                </div> 
                <div class="form-group">
                    <label>Bank Code:</label>
                    <span class="value"><%=request.getParameter("vnp_BankCode")%></span>
                </div> 
                <% 
                    String dateStr = request.getParameter("vnp_PayDate");
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = inputFormat.parse(dateStr);
                    String formattedDate = outputFormat.format(date);
                %>
                <div class="form-group">
                    <label>Payment Date:</label>
                    <span class="value"><%=formattedDate%></span>
                </div>
                <a href="homepage_1" class="btn">Return to Home</a>
            </div>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>
    </body>
</html>