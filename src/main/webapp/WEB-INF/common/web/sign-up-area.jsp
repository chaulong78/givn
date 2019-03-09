<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="event_details section--padding">
    <div class="sign_up_area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="sign_up_title">Đăng ký nhận thông tin</h2>
                    <div class="ticket_form">
                        <form:form action="${pageContext.request.contextPath}/contact" method="post" modelAttribute="contact">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="name" placeholder="Tên của bạn" required>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="email" name="email" placeholder="Email" required>
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
</section>