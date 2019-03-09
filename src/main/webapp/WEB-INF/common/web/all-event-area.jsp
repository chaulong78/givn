<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<section class="event_area section--padding2">
    <div class="container">
        <div class="row">
            <div class="featured_event">
                <div class="col-lg-6 v_middle">
                    <div class="event_img">
                        <img src="${eventList[0].image}" alt="event thumbnail">
                    </div>
                </div>

                <div class="col-lg-6 v_middle">
                    <div class="featured_event_detail">
                        <h1>${eventList[0].name}</h1>
                        <ul class="date_place">
                            <li>
                                <span class="lnr lnr-calendar-full"></span>
                                <p>${eventList[0].eventTime}</p>
                            </li>
                            <li>
                                <span class="lnr lnr-map-marker"></span>
                                <p>${eventList[0].eventPlace}</p>
                            </li>
                        </ul>
                        <%--<ul class="countdown"></ul>--%>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <c:forEach var="event" items="${eventList}">
                <div class="col-lg-4 col-md-6">
                    <div class="card_style1">
                        <figure class="card_style1__info">
                            <img src="${event.image}" alt="Event card thumbnail">

                            <figcaption>
                                <a href="${event.urlName}">
                                    <h3>${event.name}</h3>
                                </a>
                                <ul class="date_place">
                                    <li>
                                        <span class="lnr lnr-calendar-full"></span>
                                        <p>${event.eventTime}</p>
                                    </li>
                                    <li>
                                        <span class="lnr lnr-map-marker"></span>
                                        <p>${event.eventPlace}</p>
                                    </li>
                                </ul>
                            </figcaption>
                            <!-- end /.figcaption -->
                        </figure>
                        <!-- end /.figure -->
                    </div>
                    <!-- end /.event_card -->
                </div>
                <!-- end /.col-lg-4 col-md-6 -->
            </c:forEach>
        </div>
        <!-- end /.row -->
    </div>
    <!-- end /.container -->
</section>