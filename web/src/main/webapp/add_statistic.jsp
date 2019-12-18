<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.12.2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/admin/event/get_not_finished_events/add_statistic" method="POST">
        <table>
            <tr>
                <th><c:out value="${event.teamOne} goals"/> </th>
                <th><c:out value="${event.teamTwo} goals"/></th>
            </tr>
            <tr>
                <td>
                    <input type="number" min="0" max="15" step="1" required name="teamOneGoals">
                </td>
                <td>
                    <input type="number" min="0" max="15" step="1" required name="teamTwoGoals">
                    <input type="hidden" name="eventId" value="${eventId}">
                </td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="make it finish" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
