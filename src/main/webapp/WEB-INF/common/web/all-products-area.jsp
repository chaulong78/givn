<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="section--padding2 bgcolor">
    <div class="shortcode_wrapper">
        <div class="container">
            <div class="row">
                <!-- start .col-md-4 -->
                <c:forEach var="course" items="${courseList}">
                    <div class="col-lg-4 col-md-6">
                        <!-- start .single-product -->
                        <div class="product product--card product--card2">
                            <div class="product__thumbnail">
                                <img src="${course.image}" alt="Product Image">
                                <div class="prod_btn">
                                    <a href="${course.urlName}" class="transparent btn--sm btn--round">Chi tiết</a>
                                    <%--<a href="#" class="transparent btn--sm btn--round">Thêm vào giỏ</a>--%>
                                </div>
                                <!-- end /.prod_btn -->
                            </div>
                            <!-- end /.product__thumbnail -->

                            <div class="product-desc">
                                <a href="${course.urlName}" class="product_title">
                                    <h4>${course.name}</h4>
                                </a>

                                <p>${course.description}</p>
                            </div>
                            <!-- end /.product-desc -->

                            <ul class="titlebtm">
                                <li class="product_cat">
                                    <a href="${course.typeUrl}">
                                        <span class="lnr lnr-book"></span>${course.typeName}</a>
                                </li>
                                <li class="rating product--rating">
                                    <ul>
                                        <li>
                                            <span class="fa fa-star"></span>
                                        </li>
                                        <li>
                                            <span class="fa fa-star"></span>
                                        </li>
                                        <li>
                                            <span class="fa fa-star"></span>
                                        </li>
                                        <li>
                                            <span class="fa fa-star"></span>
                                        </li>
                                        <li>
                                            <span class="fa fa-star"></span>
                                        </li>
                                    </ul>
                                </li>
                            </ul>

                            <div class="product-purchase">
                                <div class="price_love">
                                    <span>VNĐ ${course.price}</span>
                                </div>
                            </div>
                            <!-- end /.product-purchase -->
                        </div>
                        <!-- end /.single-product -->
                    </div>
                </c:forEach>
                <!-- end /.col-md-4 -->
            </div>
        </div>
    </div>
</section>