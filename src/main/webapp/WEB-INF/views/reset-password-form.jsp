<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Get Ins Vietnam</title>
    <link href="/css/themes/fixed-menu/materialize.css" type="text/css" rel="stylesheet">
    <link href="/css/themes/fixed-menu/style.css" type="text/css" rel="stylesheet">
    <link href="/css/layouts/page-center.css" type="text/css" rel="stylesheet">
</head>
<body class="cyan">
<c:import url="../common/pre-loading.jsp"/>
<div id="login-page" class="row">
    <div class="col s12 z-depth-4 card-panel">
        <form:form class="login-form" action="/reset-password/reset?token=${token}" method="post">
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="/login">
                        <img src="/images/logo/login-logo.png" alt=""
                             class="circle responsive-img valign profile-image-login"></a>
                    <h4>Tạo mật khẩu mới</h4>
                    <span style="color: #ff572f; font-weight: bold; text-align: center">
                        <c:out value="${message}"/>
                    </span>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="material-icons prefix pt-5">lock_outline</i>
                    <input id="password" name="password" type="password">
                    <label for="password">Mật khẩu mới</label>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="material-icons prefix pt-5">lock_outline</i>
                    <input id="passwordAgain" name="passwordAgain" type="password">
                    <label for="passwordAgain">Nhập lại mật khẩu</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn waves-effect waves-light col s12">
                        Gửi
                    </button>
                </div>
                <div class="input-field col s12">
                    <p class="margin sign-up">
                        <a href="/login">Đăng nhập</a>
                        <a href="/register" class="right">
                            Đăng ký
                        </a>
                    </p>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="/vendors/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>
</body>
</html>