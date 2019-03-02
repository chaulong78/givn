<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

<div style="padding-left:16px">

</div>

</body>
</html>
