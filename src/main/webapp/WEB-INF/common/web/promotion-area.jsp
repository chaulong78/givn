<%@ page contentType="text/html; charset=UTF-8" %>
<section class="promotion-area">
    <div class="container">
        <div class="row">
            <div class="col-md-6 v_middle">
                <div class="promotion-img">
                    <img src="/webstatic/src/images/kawai.png" alt="Promotion image">
                </div>
                <!-- end /.promotion-img -->
            </div>
            <!-- end /.col-md-6 -->

            <div class="col-lg-5 offset-lg-1 col-md-6 v_middle">
                <div class="promotion-content">
                    <h3 class="promotion__subtitle">Sự kiện đặc biệt</h3>
                    <h1 class="promotion__title">${event.name}
                    </h1>
                    <p>${event.description}</p>
                    <a href="${event.urlName}" class="btn btn--lg btn--round">Xem chi tiết</a>
                </div>
                <!-- end /.promotion-content -->
            </div>
            <!-- end /.col-md-5 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>