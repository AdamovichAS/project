<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.11.2019
  Time: 14:32
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

<a href="${pageContext.request.contextPath}/betDTO"><fmt:message key="my_page.betDTO" bundle="${messages}"/></a>

<c:if test="${userBets ne null}">
    <H3>Your bets</H3>
    <form action="${pageContext.request.contextPath}/cancel_bet" method="POST">
        <select name="betId">
            <c:forEach items="${userBets}" var="item">
                <option value="${item.id}">${item}</option>
            </c:forEach>
            <input type="submit" name="submit"
                   value="<fmt:message key="my_page.cancel_bet" bundle="${messages}"/>"/><br>
        </select>

    </form>
</c:if>

</body>
</html>
