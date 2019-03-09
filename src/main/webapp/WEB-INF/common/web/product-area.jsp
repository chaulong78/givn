<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="products">
    <!-- start container -->
    <div class="container">
        <!-- start row -->
        <div class="row">
            <!-- start .col-md-12 -->
            <div class="col-md-12">
                <div class="sorting">
                    <span style="font-size: large"><b>Thể loại khóa học: </b></span>
                    <ul>
                        <c:forEach var="type" items="${courseTypeList}">
                            <li>
                                <a href="${type.urlName}">${type.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!-- end /.col-md-12 -->
        </div>
        <!-- end /.row -->

        <div class="row">
            <!-- start .col-md-4 -->
            <c:forEach var="course" items="${courseList}">
                <div class="col-lg-4 col-md-6">
                    <!-- start .single-product -->
                    <div class="product product--card product--card2">

                        <div class="product__thumbnail">
                            <img src="${course.image}" alt="Product Image">
                            <div class="prod_btn">
                                <a href="${course.urlName}" class="transparent btn--sm btn--round">Chi
                                    tiết</a>
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
                            <div class="sell">
                                <p>
                                    <span>${course.duration} buổi</span>
                                </p>
                            </div>
                        </div>
                        <!-- end /.product-purchase -->
                    </div>
                    <!-- end /.single-product -->
                </div>
            </c:forEach>
            <!-- end /.col-md-4 -->

            <div class="col-md-12">
                <div class="more-product">
                    <a href="${pageContext.request.contextPath}/khoa-hoc" class="btn btn--lg btn--round">Tất cả khóa học</a>
                </div>
            </div>
            <!-- end ./col-md-12 -->
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>