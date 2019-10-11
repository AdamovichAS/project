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
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${leagueId ne null}">

        <form action="${pageContext.request.contextPath}/new_event/chose_league/chose_event_info" method="POST">
            <label>select team one:</label>
            <select name="teamOneId">
                <c:forEach items="${allTeams}" var="item">
                    <option value="${item.id}">${item}</option>
                </c:forEach>
            </select>
            <label>select team two:</label>
            <select name="teamTwoId">
                <c:forEach items="${allTeams}" var="item">
                    <option value="${item.id}">${item}</option>
                </c:forEach>
            </select>
            <div>
                <label>start time / end time</label>
                 <input type="datetime-local" required  placeholder="start time" name="start">
                <input type="datetime-local" required placeholder="end time" name="end">
            </div>
            <div>
                <label>factors</label>
                <input type="number" step="0.01" min="0.01" placeholder="factor win first" required name="win">
                <input type="number" step="0.01" min="0.01" required  placeholder="factor draw" name="draw">
                <input type="number" step="0.01" min="0.01" required placeholder="factor lose first" name="lose">
            </div>
            <input type="submit"  value="create event"/>
        </form><br><br>
        <c:if test="${error ne null}">
            <c:out value="${error}"/>
        </c:if>

    </c:when>
    <c:when test="${savedEvent.name ne null}">
        <h3>Saved event:</h3>
        <c:out value="Id - ${savedEvent.id}"/><br>
        <c:out value="Name - ${savedEvent.name}"/><br>
        <c:out value="Start time - ${savedEvent.startTime}"/><br>
        <c:out value="End time - ${savedEvent.endTime}"/><br>
        <c:forEach items="${savedEvent.factors}" var="item">
            <c:out value="${item}"/>
        </c:forEach>
    </c:when>
    <c:otherwise>

        <form action="${pageContext.request.contextPath}/new_event/chose_league/" method="POST">
            <label>select league:</label>
            <select name="league">
                <c:forEach items="${allLeagues}" var="item">
                    <option value="${item.id}">${item}</option>
                </c:forEach>
            </select>
            <input type="submit"  value="chose league"/>
        </form>
        <c:if test="${error ne null}">
            <c:out value="${error}"/>
        </c:if>

    </c:otherwise>
</c:choose>

<br><br>

</body>
</html>
