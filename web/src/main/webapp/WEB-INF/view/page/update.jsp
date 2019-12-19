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


    <div >
        <sec:authorize access="hasAnyRole('USER_VER','USER_NOT_VER')">
        <a href="${pageContext.request.contextPath}/update?action=passport">Update passport</a>
        </sec:authorize>
        <a href="${pageContext.request.contextPath}/update?action=password">Update password</a>
    </div>
    <br>
    <c:choose>
        <c:when test="${action eq 'passport'}">
            <jsp:include page="update_passport.jsp"/>
        </c:when>
        <c:when test="${action eq 'password'}">
            <jsp:include page="update_password.jsp"/>
        </c:when>
    </c:choose>

    <c:if test="${message ne null}">
        <c:out value="${message}"/>
    </c:if>
    <c:if test="${passport ne null}">
        <c:out value="${passport}"/>
    </c:if>
</body>
</html>