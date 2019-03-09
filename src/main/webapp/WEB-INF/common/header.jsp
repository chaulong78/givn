<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "security" uri = "http://www.springframework.org/security/tags" %>
<header id = "header" class = "page-topbar">
    <!-- start header nav-->
    <div class = "navbar-fixed">
        <nav class = "navbar-color">
            <div class = "nav-wrapper">
                <ul class = "left">
                    <li>
                        <h1 class = "logo-wrapper">
                            <a href = "/admin" class = "brand-logo darken-1">
                                <img src = "/images/logo/getins-background.png" alt = "">
                                <span class = "logo-text hide-on-med-and-down">GetIns Vietnam</span>
                            </a>
                        </h1>
                    </li>
                </ul>
                <ul class = "right hide-on-med-and-down">
                    <li>
                        <a href = "javascript:void(0);" class = "waves-effect waves-block waves-light profile-button"
                           data-activates = "profile-dropdown">
                  <span class = "avatar-status avatar-online">
                    <img src = "<security:authentication property='principal.avatar'/>" alt = "avatar">
                    <i></i>
                  </span>
                        </a>
                    </li>
                    <li>
                        <a href = "#" data-activates = "chat-out"
                           class = "waves-effect waves-block waves-light chat-collapse">
                            <i class = "material-icons">format_indent_increase</i>
                        </a>
                    </li>
                </ul>
                <!-- profile-dropdown -->
                <ul id = "profile-dropdown" class = "dropdown-content">
                    <li>
                        <a href = "/" class = "grey-text text-darken-1">
                            <i class = "material-icons">home</i>Home</a>
                    </li>
                    <li>
                        <a href = "<c:url value="/logout"></c:url>" class = "grey-text text-darken-1">
                            <i class = "material-icons">keyboard_tab</i> Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <!-- end header nav-->
</header>