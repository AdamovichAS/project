<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>

<html>
<head>
    <title>Title</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/main/">Main page</a>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="index.logout" bundle="${messages}"/></a>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login"><fmt:message key="index.userlogin" bundle="${messages}"/></a>
    <a href="${pageContext.request.contextPath}/registration"><fmt:message key="index.registration" bundle="${messages}"/></a>
</sec:authorize>

</body>
</html>
