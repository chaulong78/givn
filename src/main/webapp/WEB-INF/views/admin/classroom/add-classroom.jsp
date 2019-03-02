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
                                    <c:if test="${tempFunction.id == 'ClassList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li><a href="${currentFunction.url}/add">Tạo lớp học</a></li>
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
                                    <form:form action="${pageContext.request.contextPath}/admin/course/class/add"
                                               method="post" class="col s12" modelAttribute="room">
                                        <input type="hidden" value="${room.id}" id="roomId"
                                               name="roomId">
                                        <input type="hidden" value="${room.courseId}" id="courseId"
                                               name="courseId">
                                        <input type="hidden" value="${room.teacherId}" id="teacherId"
                                               name="teacherId">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="name" name="name" type="text"
                                                       value="${room.name}" required>
                                                <label for="name">Tên lớp học</label>
                                            </div>
                                            <div class="input-field col s6 bootstrap-iso" id="begin">
                                                <input id="beginDate" name="beginDate" placeholder="YYYY-MM-DD"
                                                       type="text" value="${room.beginDate}"/>
                                                <label for="beginDate">Ngày bắt đầu</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <select id="selectc">
                                                    <c:forEach var="course" items="${courseList}">
                                                        <option value="${course.id}">${course.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <label>Loại lớp học</label>
                                            </div>
                                            <div class="input-field col s6 bootstrap-iso" id=end>
                                                <input id="endDate" name="endDate" placeholder="YYYY-MM-DD"
                                                       type="text" value="${room.endDate}"/>
                                                <label for="endDate">Ngày kết thúc</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <select id="selectt">
                                                    <c:forEach var="teacher" items="${teacherList}">
                                                        <option value="${teacher.id}">${teacher.fullName}</option>
                                                    </c:forEach>
                                                </select>
                                                <label>Giảng viên</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="studentNumber" name="studentNumber" type="number"
                                                       value="${room.studentNumber}" required>
                                                <label for="studentNumber">Số lượng học sinh</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="description" name="description" type="text"
                                                       value="${room.description}" required>
                                                <label for="description">Mô tả</label>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col s12">
                                                <input type="checkbox" id="enabled" name="enabled">
                                                <label for="enabled">Kích hoạt</label>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button class="btn cyan waves-effect waves-light right"
                                                        type="submit" name="action"
                                                        onclick="getAllId(); return confirm('Tạo lớp học mới?')">
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
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function () {
        var begin_input = $('input[id="beginDate"]'); //our date input has the name "date"
        var end_input = $('input[id="endDate"]'); //our date input has the name "date"
        var containerBegin = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "div#begin";
        var containerEnd = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "div#end";
        begin_input.datepicker({
            format: 'yyyy-mm-dd',
            container: containerBegin,
            todayHighlight: true,
            autoclose: true
        });
        end_input.datepicker({
            format: 'yyyy-mm-dd',
            container: containerEnd,
            todayHighlight: true,
            autoclose: true
        });
    });

    function getAllId() {
        $('#courseId').val($("#selectc").val());
        $('#teacherId').val($("#selectt").val());
        $('#enabled').val(true);
    }
</script>
</body>
</html>