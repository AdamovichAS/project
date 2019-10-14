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

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4><fmt:message key="my_page.hello" bundle="${messages}"/> ${authUser.login} <fmt:message key="my_page.role" bundle="${messages}"/> ${authUser.role}, <fmt:message key="my_page.deposit" bundle="${messages}"/> ${deposit}</h4><br>
    <a href="${pageContext.request.contextPath}/logout" ><fmt:message key="index.logout" bundle="${messages}"/></a>
    <br>
    <a href="${pageContext.request.contextPath}/update" ><fmt:message key="update.button" bundle="${messages}"/></a>
    <br>

        <a href="${pageContext.request.contextPath}/bet" ><fmt:message key="my_page.bet" bundle="${messages}"/></a>

    <c:if test="${userBets ne null}">
        <H3>Your bets</H3>
        <form action="${pageContext.request.contextPath}/cancel_bet" method="POST">
            <select name="betId">
                <c:forEach items="${userBets}" var="item">
                    <option value="${item.id}" >${item}</option>
                </c:forEach>
                <input type="submit" name="submit"  value="<fmt:message key="my_page.cancel_bet" bundle="${messages}"/>" /><br>
            </select>

        </form>
    </c:if>
</body>
</html>
