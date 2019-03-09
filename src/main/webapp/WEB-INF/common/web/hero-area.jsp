<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="hero-area hero--1 bgimage">
    <div class="bg_image_holder">
        <img src="${pageContext.request.contextPath}/webstatic/src/images/hero_area_bg2.png" alt="">
    </div>
    <!-- start hero-content -->
    <div class="hero-content content_above">
        <!-- start .contact_wrapper -->
        <div class="content-wrapper">
            <!-- start .container -->
            <div class="container">
                <!-- start row -->
                <div class="row">
                    <!-- start col-md-12 -->
                    <div class="col-md-12">
                        <div class="hero__content__title">
                            <h1>
                                <span class="light">Khám phá ngay</span>
                                <span class="bold">Tương lai về Vạn vật kết nối</span>
                            </h1>
                            <p class="tagline">Là một người trẻ, bạn cần chuẩn bị những gì trong kỉ nguyên công nghệ
                                số?</p>
                        </div>

                        <!-- start .hero__btn-area-->
                        <div class="hero__btn-area">
                            <a href="${pageContext.request.contextPath}/khoa-hoc" class="btn btn--round btn--lg">Tất cả khóa học</a>
                        </div>
                        <!-- end .hero__btn-area-->
                    </div>
                    <!-- end /.col-md-12 -->
                </div>
                <!-- end /.row -->
            </div>
            <!-- end /.container -->
        </div>
        <!-- end .contact_wrapper -->
    </div>
    <!-- end hero-content -->

    <!--start search-area -->
    <div class="search-area">
        <!-- start .container -->
        <div class="container">
            <!-- start .container -->
            <div class="row">
                <!-- start .col-sm-12 -->
                <div class="col-sm-12">
                    <!-- start .search_box -->
                    <div class="search_box">
                        <form:form action="${pageContext.request.contextPath}/tim-kiem" method="get" >
                            <input type="text" class="text_field" name="khoa-hoc"
                                   placeholder="Hàng trăm khóa học đang chờ đón bạn. Tìm hiểu ngay..." required>
                            <div class="search__select">
                            </div>
                            <button type="submit" class="search-btn btn--lg">Get it!</button>
                        </form:form>
                    </div>
                    <!-- end ./search_box -->
                </div>
                <!-- end /.col-sm-12 -->
            </div>
            <!-- end /.row -->
        </div>
        <!-- end /.container -->
    </div>
    <!--start /.search-area -->
</section>