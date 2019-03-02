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
    <link href="/vendors/dropify/css/dropify.min.css" type="text/css" rel="stylesheet">
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
                                    <c:if test="${tempFunction.id == 'EventManagement'}">
                                        <c:set var="parentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'PostList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li><a href="${currentFunction.url}/add">Tạo bài viết</a></li>
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
                                    <form:form action="${pageContext.request.contextPath}/admin/event/post/add"
                                               method="post" class="col s12" modelAttribute="post"
                                               enctype="multipart/form-data">
                                        <input type="hidden" value="${post.typeId}" id="typeId"
                                               name="typeId">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="subject" name="subject" type="text"
                                                       value="${post.subject}" required>
                                                <label for="subject">Tiêu đề bài viết</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <select id="select">
                                                    <c:forEach var="type" items="${typeList}">
                                                        <option value="${type.id}"
                                                                ${type.id == 1?'selected':''}}>${type.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <label>Thể loại bài viết</label>
                                            </div>
                                        </div>
                                        <br>
                                        <label style="font-size:100% ">Nội dung bài viết</label>
                                        <br>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="ccontent"
                                                                  name="content"
                                                                  class="materialize-textarea"
                                                                  required>${post.content}</textarea>
                                            </div>
                                        </div>
                                        <br>
                                        <label style="font-size:100% ">File đính kèm</label>
                                        <br>
                                        <div class="row">
                                            <div class="col s4 m6 14">
                                                <input type="file" multiple id="file" name="file" class="dropify"
                                                       data-default-file=""/>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col s12">
                                                <input type="checkbox" id="enabled" name="enabled">
                                                <label for="enabled">Kích hoạt</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button class="btn cyan waves-effect waves-light right"
                                                        type="submit" name="action"
                                                        onclick="getType(); return confirm('Tạo bài viết mới?')">
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
<script type="text/javascript" src="/js/scripts/form-file-uploads.js"></script>
<script type="text/javascript" src="/vendors/dropify/js/dropify.min.js"></script>
<script src="https://cdn.ckeditor.com/4.11.2/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('ccontent');
    function getType() {
        $('#typeId').val($("#select").val());
    }
</script>
</body>
</html>