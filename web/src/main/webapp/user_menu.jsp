<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.09.2019
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="hello ${login} you role is ${role}"/><br>
    <a href="${pageContext.request.contextPath}/logout" target="_blank">logout</a>
    <br>
    <a href="${pageContext.request.contextPath}/update" >update</a>
    <br>
</body>
</html>
