<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.11.2019
  Time: 16:11
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
<c:choose>
    <c:when test="${authUser.role eq 'USER_VER'}">

        <div >
            <a href="${pageContext.request.contextPath}/cashier?action=deposit"><fmt:message key="deposit.button" bundle="${messages}"/></a>
            <a href="${pageContext.request.contextPath}/cashier?action=withdrawal"><fmt:message key="withdrawal.button" bundle="${messages}"/></a>
        </div>
        <br>${account.login} <fmt:message key="my_page.balance" bundle="${messages}"/> ${account.value}<br>
        <c:choose>
            <c:when test="${action eq 'deposit'}">
                <jsp:include page="/deposit.jsp"/>
            </c:when>
            <c:when test="${action eq 'withdrawal'}">
                <jsp:include page="/withdrawal.jsp"/>
            </c:when>
        </c:choose>

    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/verification"><fmt:message key="verefication.button" bundle="${messages}"/></a>
    </c:otherwise>
</c:choose>



<c:if test="${message ne null}">
    <fmt:message key="${message}" bundle="${messages}"/>
</c:if>
</body>
</html>
