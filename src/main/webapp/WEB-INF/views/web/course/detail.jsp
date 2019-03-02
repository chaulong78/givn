<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }

        table, td, th {
            border: 1px solid #ddd;
            text-align: left;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 15px;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a class="active" href="/">Trang chủ</a>
    <a href="/course">Khóa học</a>
    <a href="/project">Dự án</a>
    <a href="/post">Tin tức</a>
    <a href="/about">Về Get Ins</a>
    <security:authorize access="hasRole('ANONYMOUS')">
        <a href="/login">Đăng nhập</a>
    </security:authorize>
    <security:authorize access="hasAnyRole('ADMIN', 'MANAGER', 'POSTER')">
        <a href="/admin">Quản trị</a>
    </security:authorize>
</div>
<br>
<div style="padding-left:16px">
    <a href="/course">Danh sách khóa học</a>
</div>
<br>
<div style="padding-left:16px">
    <img src="${course.image}">
    <h3>Tên khóa học: ${course.name}</h3>
    <h3>Thể loại: ${type.name}</h3>
    <h3>Tác giả: Get Ins</h3>
    <h3>Mô tả: ${course.description}</h3>
    <h3>Lộ trình học: ${course.content}</h3>
    <h3>Giá: ${course.price} VNĐ</h3>
    <h3>Thời lượng: ${course.price} buổi</h3>
    <h3>Yêu cầu học vấn: ${course.requirement}</h3>
    <h3>Đánh giá: ${course.rating}</h3>
    <a href="/course/buy?id=${course.id}">
        <button style="background-color: #008CBA;font-size: 20px;">Đặt mua</button>
    </a>
</div>
</body>
</html>
