<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <h5 class="breadcrumbs-title">
                                Trang quản lý
                            </h5>
                            <ol class="breadcrumbs">
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'SystemManagement'}">
                                        <c:set var="parentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="tempFunction" items="${functionList}">
                                    <c:if test="${tempFunction.id == 'RoleList'}">
                                        <c:set var="currentFunction" value="${tempFunction}"/>
                                    </c:if>
                                </c:forEach>
                                <li>${parentFunction.name}</li>
                                <li><a href="${currentFunction.url}">${currentFunction.name}</a></li>
                                <li>Gán chức năng</li>
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
                    <!-- DATA TABLE -->
                    <div id="table-datatable">
                        <h4 class="header">Gán chức năng cho quyền: <b>${role.name}</b></h4>
                        <div class="row section">
                            <div class="col s12">
                                <form:form
                                        action="${pageContext.request.contextPath}/admin/system/role/authorize"
                                        method="post" class="col s12"
                                        modelAttribute="functionAuthorizeDTOList">
                                    <input type="hidden" name="roleId"
                                           value="${role.id}">
                                    <table id="data-table-simple" class="responsive-table display"
                                           cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên</th>
                                            <th style="width: 50%">Cấp quyền</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên</th>
                                            <th>Cấp quyền</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach var="roleFunction"
                                                   items="${functionAuthorizeDTOList}">
                                            <tr>
                                                <th>${roleFunction.functionId}</th>
                                                <th>${roleFunction.functionName}</th>
                                                <th>
                                                    <div>
                                                        <input type="checkbox"
                                                               id="${roleFunction.functionId}.view"
                                                               name="view"
                                                               value="${roleFunction.functionId}"
                                                            ${roleFunction.canView? 'checked' :''}>
                                                        <label for="${roleFunction.functionId}.view">Xem</label>
                                                        |
                                                        <input type="checkbox"
                                                               id="${roleFunction.functionId}.create"
                                                               name="create"
                                                               value="${roleFunction.functionId}"
                                                            ${roleFunction.canCreate? 'checked' :''}>
                                                        <label for="${roleFunction.functionId}.create">Tạo</label>
                                                        |
                                                        <input type="checkbox"
                                                               id="${roleFunction.functionId}.update"
                                                               name="update"
                                                               value="${roleFunction.functionId}"
                                                            ${roleFunction.canUpdate? 'checked' :''}>
                                                        <label for="${roleFunction.functionId}.update">Sửa</label>
                                                        |
                                                        <input type="checkbox"
                                                               id="${roleFunction.functionId}.delete"
                                                               name="delete"
                                                               value="${roleFunction.functionId}"
                                                            ${roleFunction.canDelete? 'checked' :''}>
                                                        <label for="${roleFunction.functionId}.delete">Xóa</label>
                                                    </div>
                                                </th>
                                            </tr>
                                        </c:forEach>
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
                                        </tbody>
                                    </table>
                                </form:form>
                            </div>
                        </div>
                        <br>
                    </div>
                    <!-- END DATA TABLE -->
                </div>
            </div>
        </section>
    </div>
</div>
<c:import url="../../../common/footer.jsp"/>
<script type="text/javascript" src="/vendors/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/vendors/data-tables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/scripts/data-tables.js"></script>
<script type="text/javascript" src="/js/plugins.js"></script>
</body>
</html>