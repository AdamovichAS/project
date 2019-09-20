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
    <label>money:</label>
    <input type="number" required placeholder="money" name="money"><br>
    <label>password:</label>
    <input type="password" required placeholder="password" name="password"><br>
    <label>password:</label>
    <input type="password" required placeholder="repeat password" name="repeatedPassword"><br><br>
    <input type="submit" name="submit" value="Registration"/><br>
</form>
    <c:if test="${loginError != null}">
        <c:out value="${loginError}"/>
    </c:if>
</body>
</html>
