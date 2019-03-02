<%@ taglib prefix = "security" uri = "http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<aside id = "left-sidebar-nav">
    <ul id = "slide-out" class = "side-nav fixed leftside-navigation">
        <li class = "user-details cyan darken-2">
            <div class = "row">
                <div class = "col col s4 m4 l4">
                    <img src = "<security:authentication property='principal.avatar'/>" alt = ""
                         class = "circle responsive-img valign profile-image cyan">
                </div>
                <div class = "col col s8 m8 l8">
                    <a class = "btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href = "#"
                       data-activates = "profile-dropdown-nav"><security:authentication
                            property = "principal.username"/><i class = "mdi-navigation-arrow-drop-down right"></i></a>
                    <p class = "user-roal" style = "color: #fff844">
                        Admin
                    </p>
                </div>
            </div>
        </li>
        <li class = "no-padding">
            <ul class = "collapsible" data-collapsible = "accordion">
                <security:authentication var = "functionList" property = "principal.functionDTOList"/>
                <c:forEach var = "function" items = "${functionList}">
                    <c:if test = "${function.parentId == null && function.canView == true}">
                        <li class = "bold">
                            <a class = "collapsible-header waves-effect waves-cyan">
                                <i class = "material-icons">${function.icon}</i>
                                <span class = "nav-text">${function.name}</span>
                            </a>
                            <c:forEach var = "childFunction" items = "${functionList}">
                                <div class = "collapsible-body">
                                    <ul>
                                        <c:if test = "${childFunction.parentId == function.id && childFunction.canView == true}">
                                            <li>
                                                <a href = "${childFunction.url}">
                                                    <i class = "material-icons">${childFunction.icon}</i>
                                                    <span>${childFunction.name}</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </c:forEach>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
    </ul>
</aside>