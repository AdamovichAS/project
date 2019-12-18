<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 21:23
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
    <h4><fmt:message key="my_page.hello" bundle="${messages}"/> ${authUser.login} <fmt:message key="my_page.role" bundle="${messages}"/> ${authUser.role}</h4><br>
    <c:out value="${appCash}"/><br>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="index.logout" bundle="${messages}"/></a><br>
    <a href="${pageContext.request.contextPath}/admin/event/add/chose_league/"><fmt:message key="add_event.add_event" bundle="${messages}"/></a><br>
    <a href="${pageContext.request.contextPath}/admin/event/get_not_finished_events?action=addStatistic">add statistic to event, make it finish</a><br>

<c:if test="${action eq ('finish' or 'addStatistic')}">
    <jsp:include page="get_not_finished_events.jsp"/>
</c:if>

</body>
</html>
