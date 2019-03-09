<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="breadcrumb-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="breadcrumb">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/">Trang chủ</a>
                        </li>
                        <c:set var="current" value="${requestScope['javax.servlet.forward.request_uri']}"/>
                        <c:choose>
                            <c:when test="${current == '/khoa-hoc/' || current == '/khoa-hoc'}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/khoa-hoc">Khóa học</a>
                                </li>
                                <c:set var="title" value="Tất cả khóa học"/>
                            </c:when>
                            <c:when test="${current == '/tin-tuc/' || current == '/tin-tuc'}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/tin-tuc">Tin tức</a>
                                </li>
                                <c:set var="title" value="Tất cả tin tức"/>
                            </c:when>
                            <c:when test="${current == '/su-kien/' || current == '/su-kien'}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/su-kien">Sự kiện</a>
                                </li>
                                <c:set var="title" value="Tất cả sự kiện"/>
                            </c:when>
                            <c:when test="${fn:contains(current, '/tim-kiem')}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/khoa-hoc/">Khóa học</a>
                                </li>
                                <c:set var="title" value="Tìm kiếm từ khóa: ${key}"/>
                            </c:when>
                            <c:when test="${current == '/about-us/' || current == '/about-us'}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/about-us">Về chúng tôi</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <C:when test="${fn:contains(current, '/danh-muc/')}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/khoa-hoc">Khóa học</a>
                                        </li>
                                        <li class="active">
                                            <a href="${current}">${courseList[0].typeName}</a>
                                        </li>
                                    </C:when>
                                </c:choose>
                                <c:choose>
                                    <C:when test="${fn:contains(current, '/danh-muc-tin/')}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/tin-tuc">Tin tức</a>
                                        </li>
                                        <li class="active">
                                            <a href="${current}">${postList[0].typeName}</a>
                                        </li>
                                    </C:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <h1 class="page-title">${title}</h1>
            </div>
            <!-- end /.col-md-12 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>