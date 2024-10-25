
<div class="page-wraper popup" id="register-popup" style="margin-top: 50px">
    <div class="account-form popup-content">
        <div class="account-form-inner">
            <div style="text-align: right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="close-register-popup"></button></div>
            <div class="account-container">                     
                <div class="heading-bx left">      
                    <h2 class="title-head">Sign Up <span>Now</span></h2>
                </div>	
                <form class="contact-bx" action="register" method="post">
                    <div class="row placeani">

                        <div class="col-lg-12">
                            <p style="color: red" id="email-error">${requestScope.email_error}</p>
                            <p style="color: red" id="check-email-error" hidden>Email existed!</p>
                            <div class="form-group">
                                <label>Your Email Address</label>
                                <input name="email" type="email" required="" class="form-control" value="${requestScope.email}">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Password</label>
                                <input name="pass" type="password" class="form-control" required="">     
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="form-group">
                                <p style="color: red" id="pass-error">${requestScope.pass_error}</p>
                                <p style="color: red" id="check-pass-error" hidden>Wrong confirmed password!</p>
                                <label>Confirm Password</label>
                                <input name="confirmedPass" type="password" class="form-control" required="">
                            </div>
                        </div>
                        <div class="col-lg-12 m-b30">
                            <button name="submit" type="submit" value="Submit" class="btn button-md">Sign Up</button>
                        </div>
                        <div class="col-lg-12">
                            <h6>Sign Up with Social media</h6>
                            <div class="d-flex">
                                <a class="btn flex-fill m-r5 facebook" href="https://www.facebook.com/v20.0/dialog/oauth?
                                   client_id=881147463974671&redirect_uri=http://localhost:8080/quizonline/facebooklogin"><i class="fa fa-facebook"></i>Facebook</a>
                                <a class="btn flex-fill m-l5 google-plus" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/quizonline/googlelogin&response_type=code
                                   &client_id=393624416817-8crlnf1o21kjk410epg6pjg7q6bohd79.apps.googleusercontent.com&approval_prompt=force"><i class="fa fa-google-plus"></i>Google Plus</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
