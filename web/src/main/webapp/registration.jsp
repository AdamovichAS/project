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

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    <label><fmt:message key="index.userlogin" bundle="${messages}"/></label>
    <input type="text" required placeholder="userlogin" name="userlogin"><br>

    <label><fmt:message key="userlogin.password" bundle="${messages}"/></label>
    <input type="password" required placeholder="password" name="password"><br>
    <label><fmt:message key="registration.repeat_password" bundle="${messages}"/></label>
    <input type="password" required placeholder="repeat password" name="repeatedPassword"><br><br>
    <label><fmt:message key="registration.first_name" bundle="${messages}"/></label>
    <input type="text" required placeholder="first name" name="firstName"><br>
    <label><fmt:message key="registration.last_name" bundle="${messages}"/></label>
    <input type="text" required placeholder="last name" name="lastName"><br>
    <label><fmt:message key="registration.phone" bundle="${messages}"/></label>
    <input type="number" required placeholder="phone" name="phone"><br>
    <label><fmt:message key="registration.mail" bundle="${messages}"/></label>
    <input type="text" required placeholder="email" name="email"><br>
    <label><fmt:message key="registration.age" bundle="${messages}"/></label>
    <input type="number" required placeholder="age" name="age"><br>
    <label><fmt:message key="registration.country" bundle="${messages}"/></label>
    <input type="text" required placeholder="country" name="country"><br>

    <input type="submit" name="submit" value="<fmt:message key="index.registration" bundle="${messages}"/>"/><br>
</form>
    <c:if test="${loginError != null}">
        <c:out value="${loginError}"/>
    </c:if>
</body>
</html>
