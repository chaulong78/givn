<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="job_hero_area event_detail_breadcrumb bgimage">
    <div class="bg_image_holder">
        <img src="${event.image}" alt="">
    </div>

    <div class="container  content_above">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="job_hero_content">
                    <h1>${even.name}</h1>
                    <div class="job_date">
                        <p style="color: #ff4328; font-size: x-large">
                            <span class="lnr lnr-map-marker"></span><b> ${event.eventPlace}</b></p>
                        <p style="color: #ff4328; font-size: x-large">
                            <span class="lnr lnr-calendar-full"></span><b> ${event.eventTime}</b></p>
                    </div>
                </div>
                <!-- end /.job_hero_content -->
            </div>
            <!-- end /.col-md-8 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>