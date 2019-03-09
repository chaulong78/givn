<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="login_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form action="${pageContext.request.contextPath}/reset" method="post">
                    <div class="cardify login">
                        <div class="login--header">
                            <h3>NHẬP MẬT KHẨU MỚI</h3>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->
                        <input type="hidden" name="key" value="${key}">
                        <div class="login--form">
                            <div class="form-group">
                                <label for="password">Mật khẩu mới</label>
                                <input id="password" name="password" type="password" class="text_field">
                            </div>

                            <div class="form-group">
                                <label for="password-again">Nhập lại mật khẩu</label>
                                <input id="password-again" name="pass-again" type="password" class="text_field">
                            </div>

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