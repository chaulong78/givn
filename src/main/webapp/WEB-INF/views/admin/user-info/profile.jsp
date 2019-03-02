<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link href="/vendors/dropify/css/dropify.min.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
</head>
<body>
<c:import url="../../../common/pre-loading.jsp"/>
<c:import url="../../../common/header.jsp"/>
<!-- START MAIN -->
<div id="main">
    <div class="wrapper">
        <c:import url="../../../common/left-sidebar.jsp"/>
        <!-- START CONTENT -->
        <security:authentication var="functionList"
                                 property="principal.functionDTOList"/>
        <section id="content">
            <div id="breadcrumbs-wrapper">
                <div class="container">
                    <div class="row">
                        <div class="col s10 m6 l6">
                            <c:set var="url"
                                   value="${requestScope['javax.servlet.forward.request_uri']}"/>
                            <c:forEach var="function" items="${functionList}">
                                <c:if test="${function.url == url}">
                                    <c:set var="currentFunction" value="${function}"/>
                                </c:if>
                            </c:forEach>
                            <h5 class="breadcrumbs-title">
                                Trang quản lý
                            </h5>
                            <ol class="breadcrumbs">
                                <c:forEach var="parentFunction" items="${functionList}">
                                    <c:if test="${parentFunction.id == currentFunction.parentId}">
                                        <li>${parentFunction.name}</li>
                                        <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <%----------------------------%>
            <div class="container">
                <div class="section">
                    <p style="color: #ff572f; font-weight: bold; text-align: center">
                        <c:out value="${message}"/>
                        <c:remove var="message"/>
                    </p>
                    <div class="divider"></div>
                    <%-- PROFILE FORM --%>
                    <div class="row section">
                        <div class="col s12 m12 l12">
                            <div class="card-panel">
                                <div class="row">
                                    <form:form action="${pageContext.request.contextPath}/admin/info/profile"
                                               method="post" class="col s12" modelAttribute="profileDTO"
                                               enctype="multipart/form-data">
                                        <input type="hidden" name="id" value="${profileDTO.id}">
                                        <input type="hidden" name="userName" value="${profileDTO.userName}">
                                        <input type="hidden" name="avatar" value="${profileDTO.avatar}">
                                        <div class="row">
                                            <div class="col s3 m4 15">
                                                <img src="${profileDTO.avatar}"
                                                     alt="Avatar"
                                                     class="ct-square responsive-img valign profile-image cyan">
                                            </div>
                                            <div class="col s4 m2 14">
                                                <input type="file" id="file" name="file" class="dropify"
                                                       data-default-file=""/>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col s6">
                                                    <input id="userName" type="text"
                                                           disabled="disabled" value="${profileDTO.userName}"
                                                           required>
                                                    <label for="userName">Tên đăng nhập</label>
                                                </div>
                                                <div class="input-field col s6">
                                                    <input id="email" name="email" type="email"
                                                           value="${profileDTO.email}" required>
                                                    <label for="email">Email</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="fullName" name="fullName" type="text"
                                                       value="${profileDTO.fullName}">
                                                <label for="fullName">Họ và tên</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="address" name="address" type="text"
                                                       value="${profileDTO.address}">
                                                <label for="address">Địa chỉ</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6 bootstrap-iso" id="birth">
                                                <input id="birthDate" name="birthDate" placeholder="YYYY-MM-DD"
                                                       type="text" value="${profileDTO.birthDate}"/>
                                                <label for="birthDate">Ngày sinh</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="phone" name="phone" type="text"
                                                       value="${profileDTO.phone}">
                                                <label for="phone">Điện thoại</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <select id="gender" name="gender">
                                                    <c:if test="${profileDTO.gender == false}">
                                                        <option value="0" selected>Nam</option>
                                                        <option value="1">Nữ</option>
                                                    </c:if>
                                                    <c:if test="${profileDTO.gender == true}">
                                                        <option value="0">Nam</option>
                                                        <option value="1" selected>Nữ</option>
                                                    </c:if>
                                                </select>
                                                <label>Giới tính</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="job" name="job" type="text"
                                                       value="${profileDTO.job}">
                                                <label for="job">Nghề nghiệp</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button class="btn cyan waves-effect waves-light right"
                                                        type="submit" name="action"
                                                        onclick="return confirm('Lưu thông tin?')">Submit
                                                    <i class="material-icons right">send</i>
                                                </button>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END PROFILE FORM -->
                </div>
            </div>
        </section>
        <br>
    </div>
</div>
<!-- END MAIN -->
<c:import url="../../../common/footer.jsp"/>
<script type="text/javascript" src="/vendors/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>
<script type="text/javascript" src="/js/scripts/form-file-uploads.js"></script>
<script type="text/javascript" src="/vendors/dropify/js/dropify.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var date_input = $('input[id="birthDate"]');
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "div#birth";
        date_input.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true
        });
    })
</script>
</body>
</html>