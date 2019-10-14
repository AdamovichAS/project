<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 21:23
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
    <h4><fmt:message key="my_page.hello" bundle="${messages}"/> ${authUser.login} <fmt:message key="my_page.role" bundle="${messages}"/> ${authUser.role}</h4><br>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="index.logout" bundle="${messages}"/></a><br><br>
    <a href="${pageContext.request.contextPath}/new_event/chose_league/"><fmt:message key="add_event.add_event" bundle="${messages}"/></a><br><br>




</body>
</html>
