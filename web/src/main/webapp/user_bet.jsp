<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.11.2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>


<%--<c:if test="${userBets ne null}">--%>
<%--    <H3>Your bets</H3>--%>
<%--    <form action="${pageContext.request.contextPath}/cancel_bet" method="POST">--%>
<%--        <select name="betId">--%>
<%--            <c:forEach items="${userBets}" var="item">--%>
<%--                <option value="${item.id}">${item}</option>--%>
<%--            </c:forEach>--%>
<%--            <input type="submit" name="submit"--%>
<%--                   value="<fmt:message key="my_page.cancel_bet" bundle="${messages}"/>"/><br>--%>
<%--        </select>--%>

<%--    </form>--%>
<%--</c:if>--%>

<%--<c:if test="${userBets ne null}">--%>
<div class="row col-md-6">
    <form action="${pageContext.request.contextPath}/cancel_bet" method="POST">
    <table class="table table-striped table-bordered table-sm">
        <H3>Your bets</H3>
        <tr>
            <th>bet</th>
            <th>check box</th>
        </tr>

        <c:forEach items="${userBets}" var="bet">
            <tr>
                <td>${bet}</td>
                <td><input class="form-check-input" type="checkbox" name="betId" value="${bet.id}"></td>
            </tr>
        </c:forEach>
    </table>
        <input type="submit" name="button"  value="Cancel bet" />
    </form>
</div>

    <nav aria-label="...">
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/bet_pagination?currentPage=${currentPage-1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${maxPages}" var="page">
                <c:choose>
                    <c:when test="${currentPage == page}">
                        <li class="page-item active" aria-current="page">
                            <a class="page-link" >${page}<span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/bet_pagination?currentPage=${page}">${page}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < maxPages}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/bet_pagination?currentPage=${currentPage+1}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
<%--</c:if>--%>
</body>
</html>
