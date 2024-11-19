<%@ page import="org.example.lab2.repository.PointsRepository" %>
<%@ page import="org.example.lab2.models.Point" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Results</title>
    <link rel="stylesheet" href="./styles/common.css">
    <link rel="stylesheet" type="text/css" href="./styles/table.css">
</head>
<body>
<h1>Point results</h1>
<table>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Result</th>
    </tr>
    <%
        PointsRepository repo = (PointsRepository) session.getAttribute("repo");
        List<Point> points = repo.getPoints();
        for (Point point : points) {
    %>
        <tr>
            <td><%= point.x() %></td>
            <td><%= point.y() %></td>
            <td><%= point.r() %></td>
            <td><%= point.result() %></td>
        </tr>
    <% } %>
</table>

<a href="index.jsp">Back to input form</a>
</body>
</html>

