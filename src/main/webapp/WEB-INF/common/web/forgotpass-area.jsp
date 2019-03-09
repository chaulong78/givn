<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="login_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form action="${pageContext.request.contextPath}/forgot-pass" method="post">
                    <div class="cardify login">
                        <div class="login--header">
                            <h3>KHÔI PHỤC MẬT KHẨU</h3>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->

                        <div class="login--form">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input id="email" name="email" type="text" class="text_field" required>
                            </div>

                            <button class="btn btn--md btn--round" type="submit">Gửi</button>
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