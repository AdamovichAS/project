<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.10.2019
  Time: 17:00
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
    <c:when test="${leagueId ne null}">

        <form action="${pageContext.request.contextPath}/admin/event/add/chose_league/add_info/create" method="POST">
            <label><fmt:message key="add_event.team_one" bundle="${messages}"/></label>
            <select name="teamOne">
                <c:forEach items="${allTeams}" var="item">
                    <option value="${item.name}">${item}</option>
                </c:forEach>
            </select>
            <label><fmt:message key="add_event.team_two" bundle="${messages}"/></label>
            <select name="teamTwo">
                <c:forEach items="${allTeams}" var="item">
                    <option value="${item.name}">${item}</option>
                </c:forEach>
            </select>
            <div>
                <label><fmt:message key="add_event.time" bundle="${messages}"/></label>
                 <input type="datetime-local" required  placeholder="start time" name="start">
                <input type="datetime-local" required placeholder="end time" name="end">
            </div>
            <div>
                <label><fmt:message key="add_event.factorDTOS" bundle="${messages}"/></label>
                <input type="number" step="0.01" min="1.01" placeholder="factor win first" required name="win">
                <input type="number" step="0.01" min="1.01" required  placeholder="factor draw" name="draw">
                <input type="number" step="0.01" min="1.01" required placeholder="factor lose first" name="lose">
            </div>
            <input type="hidden" name="leagueId" value="${leagueId}">
            <input type="submit"  value="<fmt:message key="add_event.create_event" bundle="${messages}"/>"/>
        </form><br><br>
        <c:if test="${requestScope.get('error') != null}">
            <fmt:message key="${requestScope.get('error')}" bundle="${messages}"/>
        </c:if>

    </c:when>
    <c:when test="${savedEvent.name ne null}">
        <h3><fmt:message key="add_event.saved_event" bundle="${messages}"/></h3>
        <c:out value="Id - ${savedEvent.id}"/><br>
        <c:out value="Name - ${savedEvent.name}"/><br>
        <c:out value="Start time - ${savedEvent.startTime}"/><br>
        <c:out value="End time - ${savedEvent.endTime}"/><br>
        <c:out value="FACTORS"/><br>
        <c:forEach items="${savedEvent.factors}" var="item">
            <c:out value="${item}"/>
        </c:forEach>
        <br> <a href="${pageContext.request.contextPath}/admin/"><fmt:message key="index.my_page" bundle="${messages}"/></a><br>
    </c:when>
    <c:otherwise>

        <form action="${pageContext.request.contextPath}/admin/event/add/chose_league/add_info" method="POST">
            <label><fmt:message key="add_event.select_league" bundle="${messages}"/></label>
            <select name="leagueId">
                <c:forEach items="${allLeagues}" var="league">
                    <option value="${league.id}">${league}</option>
                </c:forEach>
            </select>
            <input type="submit"  value="<fmt:message key="add_event.chose_league" bundle="${messages}"/>"/>
        </form>
        <c:if test="${error ne null}">
            <c:out value="${error}"/>
        </c:if>

    </c:otherwise>
</c:choose>

<br><br>

</body>
</html>
