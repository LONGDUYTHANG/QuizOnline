
<div class="page-wraper popup" id="login-popup" style="margin-top: 50px" >
            <div class="account-form popup-content" >
                <div class="account-form-inner" style="background-color: white">
                    <div style="text-align: right">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="close-login-popup"></button></div>
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Login to your <span>Account</span></h2>
                            <p>Don't have an account? <a href="register.html">Create one here</a></p>
                        </div>	
                        <form class="contact-bx" action="login" method="post">
                            <div class="row placeani">
                                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <p style="color: red" id="login-error">${requestScope.login_error}</p>
                                        <p style="color: red" id="check-login-error" hidden>Incorrect username or password</p>
                                        <label>Email</label>
                                        <input name="email" type="email" required="" class="form-control" value="${cookie.c_user.value}">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group"> 
                                        <label>Your Password</label>
                                        <input name="userPass" type="password" class="form-control" required="">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group form-forget">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input"  id="customControlAutosizing" name="rem" ${(cookie.c_check_button!=null?'checked':'')}> 
                                            <label class="custom-control-label" for="customControlAutosizing">Remember me</label>
                                        </div>
                                        <a  href="#" class="ml-auto" id="open-requestPass-popup">Forgot Password?</a>
                                    </div>
                                </div>
                                <div class="col-lg-12 m-b30">
                                    <button name="submit" type="submit" value="Submit" class="btn button-md">Login</button>
                                </div>
                                <div class="col-lg-12">
                                    <h6>Login with Social media</h6>
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



