<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/get_not_verified_users/get_passport_info/verify" method="POST">
    <table>
        <tr>
            <th>passport info</th>
            <th>IMG</th>
            <th>
                <select name="newVerificationStatus">
                    <option value="VEREF_CANCELD">Cancel</option>
                    <option value="VEREF_PASSED">Passed</option>
                </select>
            </th>
        </tr>
        <tr>
            <td>
                <c:out value="${passportForVer}"/>
                <input type="hidden" name="login" value="${login}">
                <input type="hidden" name="currentPage" value="${currentPage}">
            </td>
            <td>
<%--                <img src="/admin/get_img?photo=${passportForVer.passFileName}">--%>
                     <a target="_blank" href="${pageContext.request.contextPath}/admin/get_img?photo=${passportForVer.passFileName}">get IMG</a><br>

            </td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value="make it finish" /></td>
        </tr>
    </table>
</form>
</body>
</html>
