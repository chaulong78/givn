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
    <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
    <link href="/css/themes/fixed-menu/materialize.css" type="text/css" rel="stylesheet">
    <link href="/css/themes/fixed-menu/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<c:import url="../../../common/pre-loading.jsp"/>
<c:import url="../../../common/header.jsp"/>
<!-- START MAIN -->
<div id="main">
    <div class="wrapper">
        <c:import url="../../../common/left-sidebar.jsp"/>
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
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'CourseManagement'}">
                                        <c:set var="parentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'CourseTypeList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li>Sửa thể loại</li>
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
                    <!-- FORM ADVANCE -->
                    <div class="row section">
                        <div class="col s12 m12 l12">
                            <div class="card-panel">
                                <div class="row">
                                    <form:form
                                            action="${pageContext.request.contextPath}/admin/event/post/type/edit"
                                            method="post" class="col s12" modelAttribute="type">
                                        <input type="hidden" name="id" value="${type.id}">
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="name" name="name" type="text"
                                                       value="${type.name}" required>
                                                <label for="name">Tên thể loại</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="description" name="description" type="text"
                                                       value="${type.description}" required>
                                                <label for="description">Mô tả</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button class="btn cyan waves-effect waves-light right"
                                                        type="submit" name="action"
                                                        onclick="return confirm('Lưu thông tin?')">
                                                    Submit
                                                    <i class="material-icons right">send</i>
                                                </button>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END FORM ADVANCE -->
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
</body>
</html>