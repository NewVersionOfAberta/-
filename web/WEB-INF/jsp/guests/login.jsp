<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<h1>Authorization</h1>
<form action="${pageContext.request.contextPath}/rent/auth" method="post">
    <input type="hidden" name="command" value="authorization">
    <label for="login">Username</label>
    <input id="login" name="login" type="text">
    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <input type="submit">
</form>
</body>
</html>