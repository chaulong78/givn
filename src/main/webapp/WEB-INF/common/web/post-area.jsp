<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="blog_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="single_blog blog--default">
                    <article>
                        <figure>
                            <img src="${post.image}" alt="Blog image">
                        </figure>
                        <div class="blog__content">
                            <a href="${post.urlName}" class="blog__title">
                                <h4>${post.name}</h4>
                            </a>

                            <div class="blog__meta">
                                <div class="author">
                                    <span class="lnr lnr-user"></span>
                                    <p>Tạo bởi
                                        <a href="${pageContext.request.contextPath}/about-us">Get Ins</a>
                                    </p>
                                </div>
                                <div class="date_time">
                                    <span class="lnr lnr-clock"></span>
                                    <p>${post.createDate}</p>
                                </div>
                            </div>
                        </div>

                        <div class="single_blog_content">
                            ${post.content}
                        </div>
                    </article>
                </div>
            </div>
            <!-- end /.col-md-8 -->

            <div class="col-lg-4">
                <aside class="sidebar sidebar--blog">
                    <div class="sidebar-card card--search card--blog_sidebar">
                        <div class="card-title">
                            <h4>Khóa học</h4>
                        </div>
                        <!-- end /.card-title -->

                        <div class="card_content">
                            <form:form action="${pageContext.request.contextPath}/tim-kiem" method="get" >
                                <div class="searc-wrap">
                                    <input type="text" name="khoa-hoc" placeholder="Tìm kiếm khóa học..." required>
                                    <button type="submit" class="search-wrap__btn">
                                        <span class="lnr lnr-magnifier"></span>
                                    </button>
                                </div>
                            </form:form>
                        </div>
                        <!-- end /.card_content -->
                    </div>
                    <!-- end /.sidebar-card -->

                    <div class="sidebar-card sidebar--post card--blog_sidebar">
                        <div class="card-title">
                            <!-- Nav tabs -->
                            <ul class="nav post-tab" role="tablist">
                                <li>
                                    <a href="#popular" id="popular-tab" class="active" aria-controls="popular" role="tab" data-toggle="tab" aria-selected="true">Nổi bật</a>
                                </li>
                                <li>
                                    <a href="#latest" id="latest-tab" aria-controls="latest" role="tab" data-toggle="tab" aria-selected="false">Mới nhất</a>
                                </li>
                            </ul>
                        </div>
                        <!-- end /.card-title -->

                        <div class="card_content">
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade show active" id="popular" aria-labelledby="popular-tab">
                                    <ul class="post-list">
                                        <c:forEach var="relatedPost" items="${newPostList}">
                                        <li>
                                            <div class="thumbnail_img">
                                                <img src="${relatedPost.image}" alt="blog thumbnail">
                                            </div>
                                            <div class="title_area">
                                                <a href="${relatedPost.urlName}">
                                                    <h4>${relatedPost.name}</h4>
                                                </a>
                                                <div class="date_time">
                                                    <span class="lnr lnr-clock"></span>
                                                    <p>${relatedPost.createDate}</p>
                                                </div>
                                            </div>
                                        </li>
                                        </c:forEach>
                                    </ul>
                                    <!-- end /.post-list -->
                                </div>
                                <!-- end /.tabpanel -->

                                <div role="tabpanel" class="tab-pane fade" id="latest" aria-labelledby="latest-tab">
                                    <ul class="post-list">
                                        <c:forEach var="relatedPost" items="${newPostList}">
                                            <li>
                                                <div class="thumbnail_img">
                                                    <img src="${relatedPost.image}" alt="blog thumbnail">
                                                </div>
                                                <div class="title_area">
                                                    <a href="${relatedPost.urlName}">
                                                        <h4>${relatedPost.name}</h4>
                                                    </a>
                                                    <div class="date_time">
                                                        <span class="lnr lnr-clock"></span>
                                                        <p>${relatedPost.createDate}</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <!-- end /.post-list -->
                                </div>
                            </div>
                            <!-- end /.tab-content -->
                        </div>
                        <!-- end /.card_content -->
                    </div>
                    <!-- end /.sidebar-card -->

                    <div class="sidebar-card card--blog_sidebar card--tags">
                        <div class="card-title">
                            <h4>Danh mục</h4>
                        </div>

                        <ul class="tags">
                            <c:forEach var="postType" items="${postTypeList}">
                                <li>
                                    <a href="${postType.urlName}">${postType.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <!-- end /.tags -->
                    </div>
                    <!-- end /.sidebar-card -->
                </aside>
                <!-- end /.aside -->
            </div>
            <!-- end /.col-md-4 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>