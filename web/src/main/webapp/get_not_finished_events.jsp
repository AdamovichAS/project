<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.12.2019
  Time: 14:45
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
<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <H3>Not finished events</H3>
        <tr>
            <th><fmt:message key="event_pagination.name" bundle="${messages}"/></th>
            <th><fmt:message key="event_pagination.start" bundle="${messages}"/></th>
        </tr>

        <c:forEach items="${eventsList}" var="event">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/event/get_not_finished_events/add_statistic?action=addStatistic&currentPage=${currentPage}&eventId=${event.id}">${event.getName()}</a><br>
                    <c:if test="${eventId eq event.id}">
                        <jsp:include page="add_statistic.jsp"/>
                    </c:if>
                </td>
                <td>${event.getStartTime()}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="...">
    <ul class="pagination">
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/event/get_not_finished_events?action=finish?currentPage=${currentPage-1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${maxPages}" var="page">
            <c:choose>
                <c:when test="${currentPage == page}">
                    <li class="page-item active" aria-current="page">
                        <a class="page-link">${page}<span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/event/get_not_finished_events?action=finish?currentPage=${page}">${page}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < maxPages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/event/get_not_finished_events?action=finish?currentPage=${currentPage+1}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
