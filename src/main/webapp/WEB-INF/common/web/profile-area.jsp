<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="signup_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <form:form class="login-form" action="/profile" method="post"
                           modelAttribute="profile" enctype="multipart/form-data">
                    <div class="cardify signup_form">
                        <div class="login--header">
                            <h3>THÔNG TIN TÀI KHOẢN</h3>
                            <span style="color: #ff572f; font-weight: bold; text-align: center"><c:out
                                    value="${message}"/></span>
                        </div>
                        <!-- end .login_header -->

                        <div class="login--form">
                            <input type="hidden" name="id" value="${profile.id}">

                            <div class="form-group">
                                <label for="avatar">Avatar</label>
                                <div class="author__avatar">
                                    <img id="avatar" src="${profile.avatar}" alt="${profile.fullName}"
                                         style="width:100%;height:50%;">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="avatar">Upload ảnh</label>
                                <div class="custom_upload">
                                    <label for="thumbnail">
                                        <input type="file" id="thumbnail" class="files" name="file"
                                               data-default-file="">
                                        <span class="btn btn--round btn--sm">Chọn file</span>
                                    </label>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <input id="email" name="email" type="email" class="text_field" value="${profile.email}" required>
                            </div>

                            <div class="form-group">
                                <label for="fullname">Họ và tên</label>
                                <input id="fullname" name="fullName" type="text" class="text_field"
                                       value="${profile.fullName}">
                            </div>

                            <div class="form-group">
                                <label for="datp1">Ngày sinh</label>
                                <div class="input_with_icon">
                                    <input id="datp1" name="birthDate" type="text"
                                           class="dattaPikkara hasDatepicker"
                                           value="${profile.birthDate}">
                                    <span class="lnr lnr-calendar-full"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="category">Giới tính</label>
                                <div class="select-wrap select-wrap2">
                                    <select name="gender" id="category" class="text_field">
                                        <option value="${true}" ${proflie.gender?'selected':''}>Nam</option>
                                        <option value="${false}" ${proflie.gender?'selected':''}>Nữ</option>
                                    </select>
                                    <span class="lnr lnr-chevron-down"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address">Địa chỉ</label>
                                <input id="address" name="address" type="text" class="text_field"
                                       value="${profile.address}">
                            </div>

                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input id="phone" name="phone" type="text" class="text_field"
                                       value="${profile.phone}">
                            </div>

                            <div class="form-group">
                                <label for="job">Công việc</label>
                                <input id="job" name="job" type="text" class="text_field"
                                       value="${profile.job}">
                            </div>

                            <button class="btn btn--md btn--round register_btn" type="reset">Hủy</button>
                            <button class="btn btn--md btn--round register_btn" type="submit">Xác nhận</button>
                        </div>
                        <!-- end .login--form -->
                    </div>
                    <!-- end .cardify -->
                </form:form>
            </div>
            <!-- end .col-md-6 -->
        </div>
        <!-- end .row -->
    </div>
    <!-- end .container -->
</section>