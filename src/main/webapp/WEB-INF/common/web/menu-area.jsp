<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="menu-area">
    <!-- start .top-menu-area -->
    <div class="top-menu-area">
        <!-- start .container -->
        <div class="container">
            <!-- start .row -->
            <div class="row">
                <!-- start .col-md-3 -->
                <div class="col-lg-3 col-md-3 col-6 v_middle">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}/">
                            <img src="${pageContext.request.contextPath}/webstatic/src/images/logo1.png" alt="logo image" class="img-fluid">
                        </a>
                    </div>
                </div>
                <!-- end /.col-md-3 -->

                <!-- start .col-md-5 -->
                <div class="col-lg-8 offset-lg-1 col-md-9 col-6 v_middle">
                    <!-- start .author-area -->
                    <div class="author-area">
                        <br>
                        <sec:authorize access="hasRole('ANONYMOUS')">
                            <a href="${pageContext.request.contextPath}/login" class="author-area__seller-btn inline">Đăng nhập</a>
                            <a href="${pageContext.request.contextPath}/signup" class="author-area__seller-btn inline">Đăng kí</a>
                        </sec:authorize>
                        <div class="author__notification_area">
                            <ul>
                                <li class="has_dropdown">
                                    <div class="icon_wrap">
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!--start .author__notification_area -->

                        <!--start .author-author__info-->
                        <div class="author-author__info inline has_dropdown">
                            <sec:authorize access="!hasRole('ANONYMOUS')">
                                <div class="author__avatar">
                                    <img src="<sec:authentication property="principal.avatar"/>" alt="user avatar"
                                         style="width: 50px; height: 50px">
                                </div>
                                <div class="autor__info">
                                    <p class="name">
                                        <sec:authentication property="principal.username"/>
                                    </p>
                                </div>
                                <div class="dropdown dropdown--author">
                                    <ul>
                                        <sec:authorize access="!hasAnyRole('USER', 'ANONYMOUS')">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/admin">
                                                    <span class="lnr lnr-lock"></span>Quản trị web</a>
                                            </li>
                                        </sec:authorize>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/profile">
                                                <span class="lnr lnr-user"></span>Thông tin cá nhân</a>
                                        </li>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/change-pass">
                                                <span class="lnr lnr-cog"></span>Đổi mật khẩu</a>
                                        </li>

                                        <li>
                                            <a href="${pageContext.request.contextPath}/logout">
                                                <span class="lnr lnr-exit"></span>Đăng xuất</a>
                                        </li>
                                    </ul>
                                </div>
                            </sec:authorize>
                        </div>
                        <!--end /.author-author__info-->
                    </div>
                    <!-- end .author-area -->
                </div>
                <!-- end /.col-md-5 -->
            </div>
            <!-- end /.row -->
        </div>
        <!-- end /.container -->
    </div>
    <!-- end  -->

    <!-- start .mainmenu_area -->
    <div class="mainmenu">
        <!-- start .container -->
        <div class="container">
            <!-- start .row-->
            <div class="row">
                <!-- start .col-md-12 -->
                <div class="col-md-12">
                    <div class="navbar-header">
                        <!-- start mainmenu__search -->
                        <div class="mainmenu__search">
                            <form:form action="${pageContext.request.contextPath}/tim-kiem" method="get" >
                                <div class="searc-wrap">
                                    <input type="text" name="khoa-hoc" placeholder="Bạn muốn học gì?">
                                    <button type="submit" class="search-wrap__btn">
                                        <span class="lnr lnr-magnifier"></span>
                                    </button>
                                </div>
                            </form:form>
                        </div>
                        <!-- start mainmenu__search -->
                    </div>

                    <nav class="navbar navbar-expand-md navbar-light mainmenu__menu">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                                aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="has_dropdown">
                                    <a href="${pageContext.request.contextPath}/">Trang chủ</a>
                                </li>
                                <li class="has_dropdown">
                                    <a href="${pageContext.request.contextPath}/khoa-hoc">Khóa học</a>
                                    <div class="dropdown dropdown--menu">
                                        <ul>
                                            <c:forEach var="courseType" items="${courseTypeList}">
                                                <li>
                                                    <a href="${courseType.urlName}">Khóa học ${courseType.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </li>
                                <li class="has_dropdown">
                                    <a href="${pageContext.request.contextPath}/tin-tuc">Tin tức</a>
                                    <div class="dropdown dropdown--menu">
                                        <ul>
                                            <c:forEach var="postType" items="${postTypeList}">
                                                <li>
                                                    <a href="${postType.urlName}">Tin tức ${postType.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </li>
                                <li class="has_megamenu">
                                    <a href="${pageContext.request.contextPath}/su-kien">Sự kiện</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/about-us">Về chúng tôi</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>
                <!-- end /.col-md-12 -->
            </div>
            <!-- end /.row-->
        </div>
        <!-- start .container -->
    </div>
    <!-- end /.mainmenu-->
</div>