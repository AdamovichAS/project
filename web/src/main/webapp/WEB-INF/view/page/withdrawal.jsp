<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/cashier/withdrawal" method="POST">
    <label><fmt:message key="withdrawal.label" bundle="${messages}"/></label>
    <input type="number" step="0.01" min="1" max="${account.value}" required placeholder="0" name="withdrawal">

    <input type="submit" name="submit" value="<fmt:message key="withdrawal.button" bundle="${messages}"/>"/>
</form>
</body>
</html>
