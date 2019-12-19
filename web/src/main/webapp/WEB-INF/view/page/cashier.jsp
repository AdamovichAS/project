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

    <sec:authorize access="hasRole('USER_VER')">
        <div >
            <a href="${pageContext.request.contextPath}/user/cashier?action=deposit"><fmt:message key="deposit.button" bundle="${messages}"/></a>
            <a href="${pageContext.request.contextPath}/user/cashier?action=withdrawal"><fmt:message key="withdrawal.button" bundle="${messages}"/></a>
        </div>
        <br>${account.login} <fmt:message key="my_page.balance" bundle="${messages}"/> ${account.value}<br>
        <c:choose>
            <c:when test="${action eq 'deposit'}">
                <jsp:include page="deposit.jsp"/>
            </c:when>
            <c:when test="${action eq 'withdrawal'}">
                <jsp:include page="withdrawal.jsp"/>
            </c:when>
        </c:choose>
    </sec:authorize>




<c:if test="${message ne null}">
    <fmt:message key="${message}" bundle="${messages}"/>
</c:if>
</body>
</html>
