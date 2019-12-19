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

<form action="${pageContext.request.contextPath}/update_password" method="POST">
    <label><fmt:message key="userlogin.password" bundle="${messages}"/></label>
    <input type="password" placeholder="new password" name="password"><br>
    <label><fmt:message key="registration.repeat_password" bundle="${messages}"/></label>
    <input type="password" required placeholder="repeat password" name="repeatedPassword"><br>

    <input type="submit" name="submit" value="<fmt:message key="update.button" bundle="${messages}"/>"/><br>
</form>
<%--<a href="${pageContext.request.contextPath}/my_page" ><fmt:message key="index.my_page" bundle="${messages}"/></a><br>--%>

<c:if test="${message ne null}">
    <c:out value="${message}"/>
</c:if>
</body>
</html>