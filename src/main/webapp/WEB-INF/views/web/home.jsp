<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <!-- viewport meta -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Get Ins - Học IOT online tốt nhất Việt Nam">
    <meta name="keywords"
          content="IOT, internet of things, vạn vật kết nôi, học online, tự học, khóa học, điện tử, lập trình, arduino, vi điều khiển, get ins, getinsvn">

    <title>Get Ins - Trang chủ</title>

    <!-- inject:css -->
    <link rel="stylesheet" href="/webstatic/src/css/animate.css">
    <link rel="stylesheet" href="/webstatic/src/css/font-awesome.min.css">
    <link rel="stylesheet" href="/webstatic/src/css/fontello.css">
    <link rel="stylesheet" href="/webstatic/src/css/jquery-ui.css">
    <link rel="stylesheet" href="/webstatic/src/css/lnr-icon.css">
    <link rel="stylesheet" href="/webstatic/src/css/owl.carousel.css">
    <link rel="stylesheet" href="/webstatic/src/css/slick.css">
    <link rel="stylesheet" href="/webstatic/src/css/trumbowyg.min.css">
    <link rel="stylesheet" href="/webstatic/src/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/webstatic/src/style.css"/>
    <!-- endinject -->

    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="16x16" href="/webstatic/src/images/favicon-getins.png">
</head>

<body class="home2 single-vendor">

<!--================================
    START MENU AREA
=================================-->
<c:import url="../../common/web/menu-area.jsp"/>
<!--================================
    END MENU AREA
=================================-->

<!--================================
    START HERO AREA
=================================-->
<c:import url="../../common/web/hero-area.jsp"/>
<!--================================
    END HERO AREA
=================================-->

<!--================================
    START FEATURE AREA
=================================-->
<c:import url="../../common/web/feature-area.jsp"/>
<!--================================
    END FEATURE AREA
=================================-->

<!--================================
    START PRODUCTS AREA
=================================-->
<c:import url="../../common/web/product-area.jsp"/>
<!--================================
    END PRODUCTS AREA
=================================-->

<!--================================
    START PROMOTION AREA
=================================-->
<c:if test="${event!=null}">
    <c:import url="../../common/web/promotion-area.jsp"/>
</c:if>
<!--================================
    END PROMOTION AREA
=================================-->

<!--================================
    START POST AREA
=================================-->
<c:import url="../../common/web/post-home-area.jsp"/>
<!--================================
    END POST AREA
=================================-->

<!--================================
    START SPECIAL FEATURES AREA
=================================-->
<c:import url="../../common/web/special-feature-area.jsp"/>
<!--================================
    END SPECIAL FEATURES AREA
=================================-->

<!--================================
    START CALL TO ACTION AREA
=================================-->
<c:import url="../../common/web/call-to-action.jsp"/>
<!--================================
    END CALL TO ACTION AREA
=================================-->

<!--================================
    START FOOTER AREA
=================================-->
<c:import url="../../common/web/footer-area.jsp"/>
<!--================================
    END FOOTER AREA
=================================-->

<!--//////////////////// JS GOES HERE ////////////////-->

<!-- inject:js -->
<script src="/webstatic/src/js/vendor/jquery/jquery-1.12.3.js"></script>
<script src="/webstatic/src/js/vendor/jquery/popper.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery/uikit.min.js"></script>
<script src="/webstatic/src/js/vendor/bootstrap.min.js"></script>
<script src="/webstatic/src/js/vendor/chart.bundle.min.js"></script>
<script src="/webstatic/src/js/vendor/grid.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery-ui.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery.barrating.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery.countdown.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery.counterup.min.js"></script>
<script src="/webstatic/src/js/vendor/jquery.easing1.3.js"></script>
<script src="/webstatic/src/js/vendor/owl.carousel.min.js"></script>
<script src="/webstatic/src/js/vendor/slick.min.js"></script>
<script src="/webstatic/src/js/vendor/tether.min.js"></script>
<script src="/webstatic/src/js/vendor/trumbowyg.min.js"></script>
<script src="/webstatic/src/js/vendor/waypoints.min.js"></script>
<script src="/webstatic/src/js/dashboard.js"></script>
<script src="/webstatic/src/js/main.js"></script>
<script src="//maps.googleapis.com/maps/api/js?key=AIzaSyBeySPFGz7DIUTrReCRQT6HYaMM0ia0knA"></script>
<script src="/webstatic/src/js/map.js"></script>
<!-- endinject -->
</body>

</html>