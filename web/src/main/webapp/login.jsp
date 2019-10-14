<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value= "${locale}"/>
<fmt:setBundle basename = "translations" var = "messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <label><fmt:message key="index.login" bundle="${messages}"/></label>
    <input type="text" required placeholder="login" name="login"><br>
    <label><fmt:message key="login.password" bundle="${messages}"/></label>
    <input type="password" required placeholder="password" name="password"><br><br>
    <input type="submit" name="submit" value="<fmt:message key="index.login" bundle="${messages}"/>" />
</form>
<c:if test="${wrongLogin ne null}">
    <c:out value="${wrongLogin}"/>
</c:if>
</body>
</html>