<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Results</title>
    <link rel="stylesheet" href="./styles/common.css">
    <link rel="stylesheet" type="text/css" href="./styles/table.css">
</head>
<body>
<h1>Point results</h1>
<table border="1">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
    </tr>
    <c:forEach var="point" items="${sessionScope.repo.points}">
        <tr>
            <td>${point.x()}</td>
            <td>${point.y()}</td>
            <td>${point.r()}</td>
            <td>${point.result()}</td>
        </tr>
    </c:forEach>
</table>

<a href="index.jsp">Back to input form</a>
</body>
</html>

