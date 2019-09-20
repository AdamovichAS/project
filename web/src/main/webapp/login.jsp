<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <label>username:</label>
    <input type="text" required placeholder="login" name="login"><br>
    <label>password:</label>
    <input type="password" required placeholder="password" name="password"><br><br>
    <input type="submit" name="submit" value="Login" />
</form>
<c:if test="${wrongLogin ne null}">
    <c:out value="${wrongLogin}"/>
</c:if>
</body>
</html>