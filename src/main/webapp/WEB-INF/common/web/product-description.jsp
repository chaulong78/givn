<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="single-product-desc">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="item-preview">
                    <div class="item__preview-slider">
                        <div class="prev-slide">
                            <img src="${course.image}}"
                                 alt="${course.name}">
                        </div>
                    </div>
                </div>
                <!-- end /.item-preview-->

                <div class="item-info">
                    <!-- end /.item-navigation -->

                    <div class="tab-content">
                        <div class="fade show tab-pane product-tab active" id="product-details">
                            <div class="tab-content-wrapper">
                                <h2>Mô tả</h2>
                                ${course.description}
                                <h2>Lộ trình học</h2>
                                ${course.content}
                            </div>
                        </div>
                        <!-- end /.tab-content -->
                    </div>
                    <!-- end /.tab-content -->
                </div>
                <!-- end /.item-info -->
            </div>
            <!-- end /.col-md-8 -->

            <div class="col-lg-4">
                <aside class="sidebar sidebar--single-product">
                    <div class="sidebar-card card-pricing">
                        <div class="price">
                            <h1>
                                <sup>VNĐ</sup>${course.price}</h1>
                        </div>
                        <!-- end /.pricing-options -->

                        <%--<div class="purchase-button">--%>
                            <%--<a href="#" class="btn btn--lg btn--round">Mua khóa học</a>--%>
                        <%--</div>--%>
                        <!-- end /.purchase-button -->
                    </div>
                    <!-- end /.sidebar--card -->

                    <div class="sidebar-card card--product-infos">
                        <div class="card-title">
                            <h4>Thông tin chi tiết</h4>
                        </div>

                        <ul class="infos">
                            <li>
                                <p class="data-label">Thể loại</p>
                                <p class="info">${course.typeName}</p>
                            </li>
                            <li>
                                <p class="data-label">Thời gian học</p>
                                <p class="info">${course.duration} buổi</p>
                            </li>
                            <li>
                                <p class="data-label">Giảng viên</p>
                                <p class="info">Get Ins</p>
                            </li>
                        </ul>
                    </div>
                    <!-- end /.aside -->

                    <div class="author-card sidebar-card ">
                        <div class="card-title">
                            <h4>Thông tin giảng viên</h4>
                        </div>

                        <div class="author-infos">
                            <div class="author_avatar">
                                <img src="/webstatic/src/images/getinslogo.png"
                                     alt="Presenting the broken author avatar :D">
                            </div>

                            <div class="author">
                                <h4>Get Ins</h4>
                            </div>
                            <!-- end /.author -->

                            <div class="social social--color--filled">
                                <ul>
                                    <li>
                                        <a href="https://www.facebook.com/GetinsVietNam/">
                                            <span class="fa fa-facebook"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.instagram.com/GetinsVietNam/">
                                            <span class="fa fa-instagram"></span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- end /.social -->

                            <div class="author-btn">
                                <a href="/about-us" class="btn btn--sm btn--round">Thông tin</a>
                            </div>
                            <!-- end /.author-btn -->
                        </div>
                        <!-- end /.author-infos -->
                    </div>
                    <!-- end /.author-card -->
                </aside>
                <!-- end /.aside -->
            </div>
            <!-- end /.col-md-4 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>