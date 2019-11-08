<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<h4><fmt:message key="my_page.hello" bundle="${messages}"/> ${authUser.login} <fmt:message key="my_page.role" bundle="${messages}"/> ${authUser.role}, <fmt:message key="my_page.balance" bundle="${messages}"/> ${account}</h4>
<br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="index.logout" bundle="${messages}"/></a>
<br>
<a href="${pageContext.request.contextPath}/update"><fmt:message key="update.button" bundle="${messages}"/></a>
<br>
<a href="${pageContext.request.contextPath}/cashier" target="_blank"><fmt:message key="cashier.button" bundle="${messages}"/></a>
<br>
<a href="${pageContext.request.contextPath}/betDTO"><fmt:message key="my_page.betDTO" bundle="${messages}"/></a>
<br>
<a href="${pageContext.request.contextPath}/bet_pagination" target="_blank">My bets</a>
<br>
<%--<c:if test="${authUser.role eq 'USER_VER'}">--%>
<%--    <jsp:include page="user_bet.jsp"/>--%>
<%--</c:if>--%>

</body>
</html>
