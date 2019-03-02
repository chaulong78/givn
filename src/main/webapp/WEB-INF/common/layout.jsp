<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "../common/taglib.jsp"></jsp:include>
<%@ page contentType = "text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale=1">
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge">
        <meta name = "msapplication-tap-highlight" content = "no">
        <meta name = "description"
              content = "Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
        <meta name = "keywords"
              content = "materialize, admin template, dashboard template, flat admin template, responsive admin template,">
        <title>Management | Get Ins Vietnam</title>
        <!-- Favicons-->
        <link rel = "icon" href = "../../images/favicon/favicon-32x32.png" sizes = "32x32">
        <!-- Favicons-->
        <link rel = "apple-touch-icon-precomposed" href = "../../images/favicon/apple-touch-icon-152x152.png">
        <!-- For iPhone -->
        <meta name = "msapplication-TileColor" content = "#00bcd4">
        <meta name = "msapplication-TileImage" content = "images/favicon/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href = "../../css/themes/fixed-menu/materialize.css" type = "text/css" rel = "stylesheet">
        <link href = "../../css/themes/fixed-menu/style.css" type = "text/css" rel = "stylesheet">
        <!-- Custome CSS-->
        <link href = "../../css/custom/custom.css" type = "text/css" rel = "stylesheet">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href = "../../vendors/prism/prism.css" type = "text/css" rel = "stylesheet">
        <link href = "../../vendors/perfect-scrollbar/perfect-scrollbar.css" type = "text/css" rel = "stylesheet">
        <link href = "../../vendors/flag-icon/css/flag-icon.min.css" type = "text/css" rel = "stylesheet">
    </head>
    <body>
        <!-- Start Page Loading -->
        <c:import url = "../common/pre-loading.jsp"/>
        <!-- End Page Loading -->

        <!-- //////////////////////////////////////////////////////////////////////////// -->

        <!-- START HEADER -->
        <c:import url = "../common/header.jsp"/>
        <!-- END HEADER -->

        <!-- //////////////////////////////////////////////////////////////////////////// -->

        <!-- START MAIN -->
        <div id = "main">
            <!-- START WRAPPER -->
            <div class = "wrapper">

                <!-- START LEFT SIDEBAR NAV-->
                <c:import url = "../common/left-sidebar.jsp"/>
                <!-- END LEFT SIDEBAR NAV-->

                <!-- //////////////////////////////////////////////////////////////////////////// -->

                <!-- START CONTENT -->
                <section id = "content">
                    <!--breadcrumbs start-->
                    <div id = "breadcrumbs-wrapper">
                        <!-- Search for small screen -->
                        <div class = "header-search-wrapper grey lighten-2 hide-on-large-only">
                            <input type = "text" name = "Search" class = "header-search-input z-depth-2"
                                   placeholder = "Explore Materialize">
                        </div>
                        <div class = "container">
                            <div class = "row">
                                <div class = "col s10 m6 l6">
                                    <h5 class = "breadcrumbs-title">Blank Page</h5>
                                    <ol class = "breadcrumbs">
                                        <li><a href = "index.html">Dashboard</a></li>
                                        <li><a href = "#">Pages</a></li>
                                        <li class = "active">Blank Page</li>
                                    </ol>
                                </div>
                                <div class = "col s2 m6 l6">
                                    <a class = "btn dropdown-settings waves-effect waves-light breadcrumbs-btn right"
                                       href = "#!" data-activates = "dropdown1">
                                        <i class = "material-icons hide-on-med-and-up">settings</i>
                                        <span class = "hide-on-small-onl">Settings</span>
                                        <i class = "material-icons right">arrow_drop_down</i>
                                    </a>
                                    <ul id = "dropdown1" class = "dropdown-content">
                                        <li><a href = "#!" class = "grey-text text-darken-2">Access<span
                                                class = "badge">1</span></a>
                                        </li>
                                        <li><a href = "#!" class = "grey-text text-darken-2">Profile<span
                                                class = "new badge">2</span></a>
                                        </li>
                                        <li><a href = "#!" class = "grey-text text-darken-2">Notifications</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--breadcrumbs end-->
                    <!--start container-->
                    <div class = "container">
                        <div class = "section">
                            <p class = "caption">A Simple Blank Page to use it for your custome design and elements.</p>
                            <div class = "divider"></div>
                        </div>
                        <!-- Floating Action Button -->
                        <div class = "fixed-action-btn " style = "bottom: 50px; right: 19px;">
                            <a class = "btn-floating btn-large">
                                <i class = "material-icons">add</i>
                            </a>
                            <ul>
                                <li>
                                    <a href = "css-helpers.html" class = "btn-floating blue">
                                        <i class = "material-icons">help_outline</i>
                                    </a>
                                </li>
                                <li>
                                    <a href = "cards-extended.html" class = "btn-floating green">
                                        <i class = "material-icons">widgets</i>
                                    </a>
                                </li>
                                <li>
                                    <a href = "app-calendar.html" class = "btn-floating amber">
                                        <i class = "material-icons">today</i>
                                    </a>
                                </li>
                                <li>
                                    <a href = "app-email.html" class = "btn-floating red">
                                        <i class = "material-icons">mail_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- Floating Action Button -->
                    </div>
                    <!--end container-->
                </section>
                <!-- END CONTENT -->

                <!-- //////////////////////////////////////////////////////////////////////////// -->

                <!-- START RIGHT SIDEBAR NAV-->
                <c:import url = "../common/right-sidebar.jsp"/>
                <!-- END RIGHT SIDEBAR NAV-->

            </div>
            <!-- END WRAPPER -->
        </div>
        <!-- END MAIN -->

        <!-- //////////////////////////////////////////////////////////////////////////// -->
        <!-- START FOOTER -->
        <c:import url = "../common/footer.jsp"/>
        <!-- END FOOTER -->
        <!-- ================================================
            Scripts
            ================================================ -->
        <!-- jQuery Library -->
        <script type = "text/javascript" src = "../../vendors/jquery-3.2.1.min.js"></script>
        <!--materialize js-->
        <script type = "text/javascript" src = "../../js/materialize.min.js"></script>
        <!--scrollbar-->
        <script type = "text/javascript" src = "../../vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <!--prism -->
        <script type = "text/javascript" src = "../../vendors/prism/prism.js"></script>
        <!--plugins.js - Some Specific JS codes for Plugin Settings-->
        <script type = "text/javascript" src = "../../js/plugins.js"></script>
        <!--custom-script.js - Add your own theme custom JS-->
        <script type = "text/javascript" src = "../../js/custom-script.js"></script>
    </body>
</html>