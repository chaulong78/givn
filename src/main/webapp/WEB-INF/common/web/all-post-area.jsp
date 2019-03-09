<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="blog_area section--padding2">
    <div class="container">
        <div class="row" data-uk-grid>
            <c:forEach var="post" items="${postList}">
                <div class="col-lg-4 col-md-6">
                    <div class="single_blog blog--card">
                        <figure>
                            <img src="${post.image}" alt="Blog image">

                            <figcaption>
                                <div class="blog__content">
                                    <a href="${post.urlName}" class="blog__title">
                                        <h4>${post.name}</h4>
                                    </a>
                                    <p>${post.description}</p>
                                </div>

                                <div class="blog__meta">
                                    <div class="date_time">
                                        <span class="lnr lnr-clock"></span>
                                        <p>${post.createDate}</p>
                                    </div>
                                </div>
                            </figcaption>
                        </figure>
                    </div>
                    <!-- end /.single_blog -->
                </div>
            </c:forEach>
            <!-- end /.col-md-4 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>