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
                                    <c:if test="${tempFunction.id == 'EventList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li>Sửa sự kiện</li>
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
                                    <form:form action="${pageContext.request.contextPath}/admin/event/edit"
                                               method="post" class="col s12" modelAttribute="event"
                                               enctype="multipart/form-data">
                                        <input type="hidden" value="${event.id}" id="id"
                                               name="id">
                                        <img src="${event.image}" style="width: 50%; height: 50%">
                                        <div class="row">

                                            <div class="input-field col s6">
                                                <input id="image" name="image" type="text"
                                                       value="${event.image}" required>
                                                <label for="image">Ảnh đại diện sự kiện</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="name" name="name" type="text"
                                                       value="${event.name}" required>
                                                <label for="name">Tên sự kiện</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="time" name="eventTime" type="text"
                                                       value="${event.eventTime}" required
                                                       placeholder="2019-03-08 4:01:50">
                                                <label for="image">Thời gian diễn ra</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input id="place" name="eventPlace" type="text"
                                                       value="${event.eventPlace}" required>
                                                <label for="name">Nơi diễn ra sự kiện</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <p class="label">Danh sách diễn giả</p>
                                            <c:forEach var="speaker" items="${speakerList}">
                                                <div class="custom_checkbox">
                                                    <input type="checkbox" id="${speaker.id}"
                                                           name="checkbox"
                                                           value="${speaker.id}"
                                                    <c:forEach var="es" items="${eventSpeakerList}">
                                                    <c:if test="${es.speakerId == speaker.id}">
                                                           checked
                                                    </c:if>
                                                    </c:forEach>
                                                    >
                                                    <label for="${speaker.id}">
                                                        <span class="shadow_checkbox"></span>
                                                        <span>${speaker.name}</span>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <br>
                                        <label style="font-size:100% ">Mô tả sự kiện</label>
                                        <br>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="description"
                                                                  name="description"
                                                                  class="materialize-textarea"
                                                                  required>${event.description}</textarea>
                                            </div>
                                        </div>
                                        <label style="font-size:100% ">Chỉ đường Google Map</label>
                                        <br>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                        <textarea id="map" name="map" class="materialize-textarea">${event.map}</textarea>
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
                                                                  required>${event.content}</textarea>
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
                                                <input type="checkbox" id="enabled"
                                                       name="enabled" ${event.enabled?'checked':''}>
                                                <label for="enabled">Kích hoạt</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button class="btn cyan waves-effect waves-light right"
                                                        type="submit" name="action"
                                                        onclick="return confirm('Cập nhật sự kiện?')">
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
<script src="https://cdn.ckeditor.com/4.5.4/full-all/ckeditor.js"></script>
<script>
    $(document).ready(function () {
        CKEDITOR.replace('ccontent');
    })
</script>
</body>
</html>