<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
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
<fmt:message key="logout.logout" bundle="${messages}"/><a href="${pageContext.request.contextPath}/index.jsp" ><fmt:message key="index.my_page" bundle="${messages}"/></a>
</body>
</html>
