<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
        <title>Title</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/redirect" ><fmt:message key="index.my_page" bundle="${messages}"/></a><br>
<c:choose>
    <c:when test="${authUser.login ne null}">
        <a href="${pageContext.request.contextPath}/logout"><fmt:message key="index.logout" bundle="${messages}"/></a><br>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="index.login" bundle="${messages}"/></a><br>
        <a href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="index.registration" bundle="${messages}"/></a><br>
    </c:otherwise>
</c:choose>
<jsp:include page="event_pagination.jsp"/>
</body>
</html>