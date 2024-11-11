<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h1>Point results</h1>

<p>Result: ${result}</p>

<h2>Points before</h2>
<table border="1">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
    </tr>
    <c:forEach var="point" items="${points}">
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

