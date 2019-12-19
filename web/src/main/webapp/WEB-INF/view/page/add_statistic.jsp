<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
