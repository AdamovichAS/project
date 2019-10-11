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
<html>
<head>
    <title>Title</title>
</head>
<body>
<H3>Your bets</H3>
<c:out value="hello ${authUser.login} you role is ${authUser.role}, deposit is ${deposit}"/><br>
    <a href="${pageContext.request.contextPath}/logout" >logout</a>
    <br>
    <a href="${pageContext.request.contextPath}/update" >update</a>
    <br>

    <a href="${pageContext.request.contextPath}/bet" >bet</a>
    <c:if test="${userBets ne null}">
        <form action="${pageContext.request.contextPath}/cancel_bet" method="POST">
            <select name="betId">
                <c:forEach items="${userBets}" var="item">
                    <option value="${item.id}" >${item}</option>
                </c:forEach>
                <input type="submit" name="submit"  value="cancel bet" /><br>
            </select>

        </form>
    </c:if>
</body>
</html>
