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
<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <H3>Not verified users</H3>
        <tr>
            <th>Login</th>
<%--            <th><fmt:message key="event_pagination.start" bundle="${messages}"/></th>--%>
        </tr>

        <c:forEach items="${passportVerif}" var="passport">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/get_not_verified_users/get_passport_info?currentPage=${currentPage}&login=${passport.userLogin}">${passport.userLogin}</a><br>
                    <c:if test="${login eq passport.userLogin}">
                        <jsp:include page="user_verefication.jsp"/>
                    </c:if>
                </td>
<%--                <td>${event.getStartTime()}</td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="...">
    <ul class="pagination">
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/get_not_verified_users?currentPage=${currentPage-1}"
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
                           href="${pageContext.request.contextPath}/admin/get_not_verified_users?currentPage=${page}">${page}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < maxPages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/get_not_verified_users?currentPage=${currentPage+1}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
