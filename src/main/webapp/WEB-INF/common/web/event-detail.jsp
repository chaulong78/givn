<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="event_details section--padding">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="event_module">
                    <h4 class="event_module__title">Tổng quan</h4>
                    <p>${event.description}</p>

                    <h4 class="event_module__title">Chi tiết</h4>
                    ${event.content}
                </div>
                <!-- end /.event_module -->

                <div class="event_module">
                    <h4 class="event_module__title">Diễn giả sự kiện</h4>

                    <div>
                        <c:forEach var="speaker" items="${speakerList}">
                            <div class="single_speaker">
                                <div class="speaker__thumbnail">
                                    <img src="${speaker.image}" alt="">
                                </div>

                                <div class="speaker__detail">
                                    <h4>${speaker.name}</h4>
                                    <span class="ocuup">${speaker.job}</span>
                                    <p>${speaker.description}
                                    </p>
                                    <div class="speaker_social">
                                        <ul>
                                            <li>
                                                <a href="https://www.facebook.com/GetinsVietNam">
                                                    <span class="fa fa-facebook"></span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="https://www.instagram.com/GetinsVietNam">
                                                    <span class="fa fa-instagram"></span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- end single_speaker-->
                    </div>
                </div>
                <!-- end /.event_module -->
            </div>
            <!-- end /.col-md-12 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->

    <div class="google_map">
        <div class="location_address">
            <div class="addres_module">
                <h4>${event.eventPlace}</h4>
                <p>
                    <span class="lnr lnr-phone-handset"></span>+84 973 755 035</p>
                <p>
                    <span class="lnr lnr-envelope"></span>getinsvietnam@gmail.com</p>
            </div>
        </div>

        ${event.map}
        <!-- end /.map -->
    </div>

    <div class="sign_up_area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="sign_up_title">Đăng ký nhận thông tin</h2>
                    <div class="ticket_form">
                        <form:form action="/contact" method="post" modelAttribute="contact">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="name" placeholder="Tên của bạn">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="email" name="email" placeholder="Email">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="phone" placeholder="Số điện thoai">
                                    </div>
                                </div>
                            </div>

                            <textarea cols="30" rows="10" name="comment" placeholder="Ý kiến của bạn"></textarea>

                            <div class="sub_btn">
                                <button type="submit" class="btn btn--round btn--default">Gửi</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end /.sign_up_area -->
</section>