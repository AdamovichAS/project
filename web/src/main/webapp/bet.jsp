<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.10.2019
  Time: 17:01
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
    <c:if test="${bet ne null}">
        <c:out value="Your bet is: ${bet}"/>
    </c:if>
    <h3>New bet</h3>
    <form action="${pageContext.request.contextPath}/bet" method="POST">
        <select name="event">
            <c:forEach items="${events}" var="item">
                <option value="${item.id}" >${item.name} | Start: ${item.startTime} | ${item.getFactors().get(0)} | ${item.getFactors().get(1)} | ${item.getFactors().get(2)} |</option><br>
            </c:forEach>
        </select>
        <select name="factor">
            <option value="win">WIN</option>
            <option value="lose">LOSE</option>
            <option value="draw">DRAW</option>
        </select>

        <input type="number" step="1" min="1" max="${user_money}" placeholder="money" name="money_for_bet" required><br>
        <input type="submit" name="submit" value="bet" />
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/user_menu.jsp">My page</a><br>

</body>
</html>
