<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="login_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form action="${pageContext.request.contextPath}/change-pass" method="post" modelAttribute="pass">
                    <div class="cardify login">
                        <div class="login--header">
                            <h3>ĐỔI MẬT KHẨU</h3>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out
                                    value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->
                        <div class="login--form">
                            <div class="form-group">
                                <label for="pass-old">Mật khẩu cũ</label>
                                <input id="pass-old" name="oldPassword" type="password" class="text_field" required>
                            </div>

                            <div class="form-group">
                                <label for="password">Mật khẩu mới</label>
                                <input id="password" name="password" type="password" class="text_field" required>
                            </div>

                            <div class="form-group">
                                <label for="password-again">Nhập lại mật khẩu</label>
                                <input id="password-again" name="passwordAgain" type="password" class="text_field" required>
                            </div>

                            <a href="${pageContext.request.contextPath}/">
                                <button class="btn btn--md btn--round" type="reset">Hủy</button>
                            </a>
                            <button class="btn btn--md btn--round" type="submit">Xác nhận</button>
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