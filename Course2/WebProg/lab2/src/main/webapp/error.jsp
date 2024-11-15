<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="./styles/error.css">
</head>
<body>
    <div class="container">
        <h2>Error</h2>
        <p>
            <c:choose>
                <c:when test="${not empty sessionScope.error}">
                    ${sessionScope.error}
                </c:when>
                <c:otherwise>
                    No error message available.
                </c:otherwise>
            </c:choose>
        </p>
        <a href="index.jsp">Back to input form</a>
    </div>
</body>
</html>
