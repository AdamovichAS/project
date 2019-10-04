<%@ page import="static java.util.Objects.nonNull" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 19:47
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
<form action="${pageContext.request.contextPath}/registration" method="POST">
    <label>username:</label>
    <input type="text" required placeholder="login" name="login"><br>

    <label>password:</label>
    <input type="password" required placeholder="password" name="password"><br>
    <label>repeat password:</label>
    <input type="password" required placeholder="repeat password" name="repeatedPassword"><br><br>
    <label>firstName:</label>
    <input type="text" required placeholder="first name" name="firstName"><br>
    <label>lastName:</label>
    <input type="text" required placeholder="last name" name="lastName"><br>
    <label>phone:</label>
    <input type="number" required placeholder="phone" name="phone"><br>
    <label>email:</label>
    <input type="text" required placeholder="email" name="email"><br>
    <label>age:</label>
    <input type="number" required placeholder="age" name="age"><br>
    <label>country:</label>
    <input type="text" required placeholder="country" name="country"><br>

    <input type="submit" name="submit" value="Registration"/><br>
</form>
    <c:if test="${loginError != null}">
        <c:out value="${loginError}"/>
    </c:if>
</body>
</html>
