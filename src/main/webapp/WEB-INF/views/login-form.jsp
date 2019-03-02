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
        <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post"
                   class="login-form">
            <div class="row">
                <div class="input-field col s12 center">
                    <a href="/login">
                        <img src="/images/logo/login-logo.png" alt=""
                             class="circle responsive-img valign profile-image-login"></a>
                    <p class="center login-form-text">GetIns Vietnam</p>
                    <c:if test="${param.error != null}">
                            <span style="color: #ff572f; font-weight: bold; text-align: center">
                                Tên tài khoản hoặc mật khẩu không đúng
                            </span>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <span style="color: #ff572f; font-weight: bold; text-align: center">
                            Bạn đã đăng xuất
                        </span>
                    </c:if>
                    <span style="color: #ff572f; font-weight: bold; text-align: center">
                        <c:out value="${message}"/>
                    </span>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="material-icons prefix pt-5">person_outline</i>
                    <input id="username" name="username" type="text">
                    <label for="username" class="center-align">Tên đăng nhập</label>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="material-icons prefix pt-5">lock_outline</i>
                    <input id="password" name="password" type="password">
                    <label for="password">Mật khẩu</label>
                </div>
            </div>
            <div class="row">
                <div class="col s12 m12 l12 ml-2 mt-3">
                    <input type="checkbox" id="remember-me" name="remember-me"/>
                    <label for="remember-me">Ghi nhớ đăng nhập</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn waves-effect waves-light col s12">Đăng nhập</button>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 m6 l6">
                    <p class="margin medium-small">
                        <a href="/register">
                            Đăng ký
                        </a>
                    </p>
                </div>
                <div class="input-field col s6 m6 l6">
                    <p class="margin right-align medium-small">
                        <a href="/forgot-password">
                            Quên mật khẩu ?
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