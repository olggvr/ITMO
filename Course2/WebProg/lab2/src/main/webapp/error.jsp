<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>Error</h2>
<p>
    <c:choose>
        <c:when test="${not empty sessionScope.errorMessage}">
            ${sessionScope.error}
        </c:when>
        <c:otherwise>
            No error message available.
        </c:otherwise>
    </c:choose>
</p>
</body>
</html>
