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
                                    <c:if test="${tempFunction.id == 'CourseManagement'}">
                                        <c:set var="parentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'CourseList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li><a href="${currentFunction.url}/add">Tạo khóa học</a></li>
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
                                    <form:form action="${pageContext.request.contextPath}/admin/course/add"
                                               method="post" class="col s12" modelAttribute="course"
                                               enctype="multipart/form-data">
                                        <input type="hidden" value="${course.typeId}" id="typeId"
                                               name="typeId">
                                        <div class="row">
                                            <div class="col s4 m4 14">
                                                <input type="file" id="file" name="file" class="dropify"
                                                       data-default-file=""/>
                                            </div>
                                            <div class="row">
                                                <div class="input-field col s7">
                                                    <input id="name" name="name" type="text"
                                                           value="${course.name}" required>
                                                    <label for="name">Tên khóa học</label>
                                                </div>
                                                <div class="input-field col s7">
                                                    <select id="select">
                                                        <c:forEach var="type" items="${typeList}">
                                                            <option value="${type.id}"
                                                                    ${type.id == 1?'selected':''}}>${type.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <label>Thể loại khóa học</label>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="input-field col s2">
                                                <input id="price" name="price" type="number"
                                                       value="${course.price}" required>
                                                <label for="price">Giá khóa học</label>
                                            </div>
                                            <div class="input-field col s2">
                                                <input id="duration" name="duration" type="number"
                                                       value="${course.duration}" required>
                                                <label for="duration">Thời lượng</label>
                                            </div>
                                            <div class="input-field col s2">
                                                <input id="rating" name="rating" type="number"
                                                       value="${course.rating}" required>
                                                <label for="rating">Đánh giá khóa học</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="videoUrl" name="videoUrl" type="text"
                                                       value="${course.videoUrl}">
                                                <label for="videoUrl">URL Video khóa học</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="description"
                                                                  name="description"
                                                                  class="materialize-textarea"
                                                                  required>${course.description}</textarea>
                                                <label for="description">Mô tả khóa học</label>
                                            </div>
                                        </div>
                                        <br>
                                        <label style="font-size:100% ">Lộ trình khóa học</label>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="ccontent"
                                                                  name="content"
                                                                  class="materialize-textarea"
                                                                  required>${course.content}</textarea>
                                            </div>
                                        </div>
                                        <label style="font-size:100% ">Yêu cầu trình độ</label>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="requirement"
                                                                  name="requirement"
                                                                  class="materialize-textarea"
                                                                  required>${course.requirement}</textarea>
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
                                                        onclick="getType(); return confirm('Tạo khóa học mới?')">
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
    CKEDITOR.replace('requirement');

    function getType() {
        $('#typeId').val($("#select").val());
    }
</script>
</body>
</html>