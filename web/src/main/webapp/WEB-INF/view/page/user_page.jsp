<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<h3<fmt:message key="my_page.hello" bundle="${messages}"/>  <fmt:message key="my_page.role" bundle="${messages}"/> <sec:authentication property="principal.role"/>, <fmt:message key="my_page.balance" bundle="${messages}"/> ${account}</h3>
<br>
<a href="${pageContext.request.contextPath}/update">Update my info</a>
<br>
<sec:authorize access="hasRole('USER_VER')">
<a href="${pageContext.request.contextPath}/user/cashier" target="_blank"><fmt:message key="cashier.button" bundle="${messages}"/></a>
<br>
<a href="${pageContext.request.contextPath}/user/bet/new"><fmt:message key="my_page.betDTO" bundle="${messages}"/></a>
<br>
</sec:authorize>
<sec:authorize access="hasRole('USER_NOT_VER')">
    To make a deposit and bets, go through verification, please<br>
    <a href="${pageContext.request.contextPath}/verification"><fmt:message key="verefication.button" bundle="${messages}"/></a>
</sec:authorize>
<%--<a href="${pageContext.request.contextPath}/bet_pagination" target="_blank">My bets</a>--%>
<%--<br>--%>
<c:if test="${userBets ne null}">
    <jsp:include page="user_bet.jsp"/>
</c:if>

</body>
</html>
