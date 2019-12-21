<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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

    <a href="${pageContext.request.contextPath}/update">Update password</a><br>
    <a href="${pageContext.request.contextPath}/admin/event/add/chose_league/"><fmt:message key="add_event.add_event" bundle="${messages}"/></a><br>
    <a href="${pageContext.request.contextPath}/admin/event/get_not_finished_events?action=addStatistic">add statistic to event, make it finish</a><br>
    <a href="${pageContext.request.contextPath}/admin/get_not_verified_users">users verification</a><br>
    <c:if test="${passportVerif ne null}">
        <jsp:include page="users_not_vereficated_pagination.jsp"/>
    </c:if>

<c:if test="${action eq ('finish' or 'addStatistic')}">
    <c:if test="${eventsList ne null}">
      <jsp:include page="get_not_finished_events.jsp"/>
    </c:if>
</c:if>

</body>
</html>
