<%@ page import="com.github.adamovichas.project.web.user.User" %><%--
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
        <label>password:</label>
        <input type="password" placeholder="new password" name="password"><br>
        <label>firstName:</label>
        <input type="text" placeholder="new first name" name="firstName"><br>
        <label>lastName:</label>
        <input type="text" placeholder="new last name" name="lastName"><br>
        <label>phone:</label>
        <input type="number" placeholder="new phone" name="phone"><br>
        <label>email:</label>
        <input type="text" placeholder="new email" name="email"><br>
        <label>country:</label>
        <input type="text" placeholder="new country" name="country"><br>

        <input type="submit" name="submit" value="new update"/><br>
    </form>
    <a href="${pageContext.request.contextPath}/user_menu.jsp" >my page</a><br>

    <c:if test="${message ne null}">
        <c:out value="${message}"/>
    </c:if>
    <c:if test="${user ne null}">
        <c:out value="${user}"/>
    </c:if>
</body>
</html>
