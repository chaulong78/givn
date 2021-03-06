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
    <link href="/vendors/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet">
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
                    <br>
                    <div class="row">
                        <c:if test="${currentFunction.canCreate}">
                            <a href="/admin/event/add"
                               class="btn waves-effect waves-light gradient-45deg-green-teal">
                                Tạo sự kiện
                            </a>
                        </c:if>
                    </div>
                    <!-- DATA TABLE -->
                    <div id="table-datatables section">
                        <h4 class="header">Bảng sự kiện</h4>
                        <div class="row">
                            <div class="col s12">
                                <table id="data-table-simple" class="responsive-table display"
                                       cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tiêu đề</th>
                                        <th>Thời gian</th>
                                        <th>Địa điểm</th>
                                        <th>Trạng thái</th>
                                        <th>#</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tiêu đề</th>
                                        <th>Thời gian</th>
                                        <th>Địa điểm</th>
                                        <th>Trạng thái</th>
                                        <th>#</th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach var="event" items="${eventList}">
                                        <tr>
                                            <td><b>${event.id}</b></td>
                                            <td><b>${event.name}</b></td>
                                            <td><b>${event.eventTime}</b></td>
                                            <td><b>${event.eventPlace}</b></td>
                                            <td><b>
                                                <span style="color: ${event.enabled? '#245bff': '#ff4f39'}">${event.enabled? 'Kích hoạt': 'Ẩn'}</span>
                                            </b></td>
                                            <td>
                                                <c:if test="${currentFunction.canUpdate}">
                                                    <a href="${pageContext.request.contextPath}/admin/event/edit?id=${event.id}"
                                                       class="btn waves-effect waves-light gradient-45deg-green-teal">
                                                        Sửa
                                                    </a>
                                                </c:if> |
                                                <c:if test="${currentFunction.canDelete}">
                                                    <a href="${pageContext.request.contextPath}/admin/event/delete?id=${event.id}"
                                                       class="btn waves-effect waves-light gradient-45deg-purple-deep-orange"
                                                       onclick="return confirm('Xóa sự kiện?')">
                                                        Xóa
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- END DATA TABLE -->
                </div>
            </div>
        </section>
    </div>
</div>
<!-- END MAIN -->
<c:import url="../../../common/footer.jsp"/>
<script type="text/javascript" src="/vendors/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/vendors/data-tables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/scripts/data-tables.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>>
</body>
</html>