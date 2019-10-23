<%@ page import="com.github.adamovichas.project.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.09.2019
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/update" method="POST">
        <label><fmt:message key="login.password" bundle="${messages}"/></label>
        <input type="password" placeholder="new password" name="password"><br>
        <label><fmt:message key="registration.first_name" bundle="${messages}"/></label>
        <input type="text" placeholder="new first name" name="firstName"><br>
        <label><fmt:message key="registration.last_name" bundle="${messages}"/></label>
        <input type="text" placeholder="new last name" name="lastName"><br>
        <label><fmt:message key="registration.phone" bundle="${messages}"/></label>
        <input type="number" placeholder="new phone" name="phone"><br>
        <label><fmt:message key="registration.mail" bundle="${messages}"/></label>
        <input type="text" placeholder="new email" name="email"><br>
        <label><fmt:message key="registration.country" bundle="${messages}"/></label>
        <input type="text" placeholder="new country" name="country"><br>

        <input type="submit" name="submit" value="<fmt:message key="update.button" bundle="${messages}"/>"/><br>
    </form>
    <a href="${pageContext.request.contextPath}/user_menu.jsp" ><fmt:message key="index.my_page" bundle="${messages}"/></a><br>

    <c:if test="${message ne null}">
        <c:out value="${message}"/>
    </c:if>
    <c:if test="${user ne null}">
        <c:out value="${user}"/>
    </c:if>
</body>
</html>
