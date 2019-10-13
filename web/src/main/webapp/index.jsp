<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Title</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/redirect" >hello</a><br>
<c:choose>
    <c:when test="${authUser.login ne null}">
        <a href="${pageContext.request.contextPath}/logout">logout</a><br>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login.jsp">login</a><br>
        <a href="${pageContext.request.contextPath}/registration.jsp">Registration</a><br>
    </c:otherwise>
</c:choose>

</body>
</html>