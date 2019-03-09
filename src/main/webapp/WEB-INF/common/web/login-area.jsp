<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="login_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
                    <div class="cardify login">
                        <div class="login--header">
                            <h3>ĐĂNG NHẬP</h3>
                            <c:if test="${param.error != null}">
                            <span style="color: #ff572f; font-weight: bold; text-align: center">
                                Tên tài khoản hoặc mật khẩu không đúng
                            </span>
                            </c:if>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->

                        <div class="login--form">
                            <div class="form-group">
                                <label for="username">Tên đăng nhập</label>
                                <input id="username" name="username" type="text" class="text_field" required>
                            </div>

                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <input id="password" name="password" type="password" class="text_field" required>
                            </div>

                            <div class="form-group">
                                <div class="custom_checkbox">
                                    <input type="checkbox" id="remember-me" name="remember-me">
                                    <label for="remember-me">
                                        <span class="shadow_checkbox"></span>
                                        <span class="label_text">Ghi nhớ đăng nhập</span>
                                    </label>
                                </div>
                            </div>

                            <button class="btn btn--md btn--round" type="submit">Đăng nhập</button>

                            <div class="login_assist">
                                <a href="${pageContext.request.contextPath}/forgot-pass">
                                    <p class="recover">Quên mật khẩu?</p></a>
                                <a href="${pageContext.request.contextPath}/signup">
                                    <p class="signup">Chưa có tài khoản?</p></a>
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