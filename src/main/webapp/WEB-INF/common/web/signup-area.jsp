<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="signup_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form class="login-form" action="${pageContext.request.contextPath}/signup" method="post"
                           modelAttribute="user">
                    <div class="cardify signup_form">
                        <div class="login--header">
                            <h3>ĐĂNG KÝ TÀI KHOẢN</h3>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out
                                    value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->

                        <div class="login--form">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input id="email" name="email" type="email" class="text_field" required>
                            </div>
                            <div class="form-group">
                                <label for="username">Tên đăng nhập</label>
                                <input id="username" name="username" type="text" class="text_field" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <input id="password" name="password" type="password" class="text_field" required>
                            </div>
                            <div class="form-group">
                                <label for="password-again">Nhập lại mật khẩu</label>
                                <input id="password-again" name="passwordAgain" type="password" class="text_field" required>
                            </div>
                            <button class="btn btn--md btn--round register_btn" type="submit">Đăng ký</button>

                            <div class="login_assist">
                                <p>Đã có tài khoản?
                                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                                </p>
                            </div>
                        </div>
                        <!-- end .login--form -->
                    </div>
                    <!-- end .cardify -->
                </form:form>
            </div>
            <!-- end .col-md-6 -->
        </div>
        <!-- end .row -->
    </div>
    <!-- end .container -->
</section>