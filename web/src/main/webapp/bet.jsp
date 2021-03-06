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

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${bet ne null}">
        <fmt:message key="bet.saved_bet" bundle="${messages}"/> ${bet}
    </c:if>
    <h3><fmt:message key="bet.new_bet" bundle="${messages}"/></h3>
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

        <input type="number" step="1" min="1" max="${user_money}" placeholder="<fmt:message key="bet.moneyDTO" bundle="${messages}"/>" name="money_for_bet" required><br>
        <input type="submit" name="submit" value="<fmt:message key="my_page.bet" bundle="${messages}"/>" />
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/user_menu.jsp"><fmt:message key="index.my_page" bundle="${messages}"/></a><br>

</body>
</html>
