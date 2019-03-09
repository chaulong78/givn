<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<footer class="footer-area">
    <div class="footer-big section--padding">
        <!-- start .container -->
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="info-footer">
                        <div class="info__logo">
                            <img src="${pageContext.request.contextPath}/webstatic/src/images/footer-logo.png" alt="footer logo">
                        </div>
                        <p class="info--text">GET INS được tạo ra với mục đích mang đến một môi trường trao đổi, học
                            hỏi kiến thức, kinh nghiệm về lĩnh vực kĩ thuật mà đặc biệt là IOT dành cho các bạn trẻ,
                            biến IOT từ một điều tưởng chừng như xa vời trở nên gần gũi và thiết thực với đời sống
                            thường ngày hơn bao giờ hết.</p>
                        <ul class="info-contact">
                            <li>
                                <span class="lnr lnr-phone info-icon"></span>
                                <span class="info">Điện thoại: +84 973 755 035</span>
                            </li>
                            <li>
                                <span class="lnr lnr-envelope info-icon"></span>
                                <span class="info">getinsvietnam@gmail.com</span>
                            </li>
                            <li>
                                <span class="lnr lnr-map-marker info-icon"></span>
                                <span class="info">Số 21 ngõ 36 Trường Chinh, Phương Đình, Thanh Xuân, Hà Nội</span>
                            </li>
                        </ul>
                    </div>
                    <!-- end /.info-footer -->
                </div>
                <!-- end /.col-md-3 -->

                <div class="col-lg-5 col-md-6">
                    <div class="footer-menu">
                        <h4 class="footer-widget-title text--white">Về công ty</h4>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/about-us">Giới thiệu</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/khoa-hoc">Hệ thống khóa học</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/tuyen-dung">Tuyển dụng</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/lien-he">Liên hệ</a>
                            </li>
                        </ul>
                    </div>
                    <!-- end /.footer-menu -->

                    <div class="footer-menu">
                        <h4 class="footer-widget-title text--white">Chính sách và điều khoản</h4>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/danh-cho-hoc-vien">Dành cho học viên</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/chinh-sach">Chính sách bảo mật</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/dieu-khoan">Điều khoản sử dụng</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/giao-dich">Giao dịch</a>
                            </li>
                        </ul>
                    </div>
                    <!-- end /.footer-menu -->
                </div>
                <!-- end /.col-md-5 -->

                <div class="col-lg-4 col-md-12">
                    <div class="newsletter">
                        <h4 class="footer-widget-title text--white">Thông báo</h4>
                        <p>Đăng ký để nhận tin tức, cập nhật và thông tin ưu đãi mới nhất!</p>
                        <div class="newsletter__form">
                            <form:form action="${pageContext.request.contextPath}/contact" method="post" modelAttribute="contact">
                                <div class="field-wrapper">
                                    <input class="relative-field rounded" type="email" name="email" placeholder="Nhập email của bạn" required>
                                    <button class="btn btn--round" type="submit">Gửi</button>
                                </div>
                            </form:form>
                        </div>

                        <!-- start .social -->
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
                    </div>
                    <!-- end /.newsletter -->
                </div>
                <!-- end /.col-md-4 -->
            </div>
            <!-- end /.row -->
        </div>
        <!-- end /.container -->
    </div>
    <!-- end /.footer-big -->

    <div class="mini-footer">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="copyright-text">
                        <p>&copy; 2019
                            <a href="${pageContext.request.contextPath}/">Get Ins Vietnam</a>. All rights reserved.
                        </p>
                    </div>

                    <div class="go_top">
                        <span class="lnr lnr-chevron-up"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>