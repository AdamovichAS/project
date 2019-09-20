<%@ page import="by.itacademy.jd2.user.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.09.2019
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/update" method="POST">
        <label>money:</label>
        <input type="number" required placeholder="${user.money}" value="${user.money}" name="money"><br>
        <label>password:</label>
        <input type="text" required placeholder="${user.password}" value="${user.password}" name="password"><br>
        <input type="submit" name="submit" value="update"/><br>
    </form>

</body>
</html>
