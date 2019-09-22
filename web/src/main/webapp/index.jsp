<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Title</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/redirect" target="_blank">hello</a><br>
<c:choose>
    <c:when test="${login ne null}">
        <a href="${pageContext.request.contextPath}/logout" target="_blank">logout</a><br>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login.jsp" target="_blank">login</a><br>
        <a href="${pageContext.request.contextPath}/registration.jsp" target="_blank">Registration</a><br>
    </c:otherwise>
</c:choose>

</body>
</html>