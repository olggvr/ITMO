<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Error</h2>
    <p>
        <%
            String errorMessage = (String) request.getAttribute("message");
        %>
        <% if (errorMessage != null) { %>
        <%= errorMessage %>
        <% } else { %>
        No error message available.
        <% } %>
    </p>
</body>
</html>
