<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.11.2019
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/deposit" method="POST">
    <label><fmt:message key="deposit.label" bundle="${messages}"/></label>
    <input type="number" step="0.01" min="1" required placeholder="0" name="deposit">

    <input type="submit" name="submit" value="<fmt:message key="deposit.button" bundle="${messages}"/>"/>
</form>
</body>
</html>
