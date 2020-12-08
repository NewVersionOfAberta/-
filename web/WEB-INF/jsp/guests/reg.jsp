<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<form method="post" action="${pageContext.request.contextPath}/test/reg">
    <input type="hidden" name="command" value="registration">
    <label for="name">Name</label>
    <input id="name" name="name" type="text">
    <label for="surname">Surname</label>
    <input id="surname" name="surname" type="text">
    <label for="login">Username</label>
    <input id="login" name="login" type="text">
    <label for="email">Email</label>
    <input id="email" name="email" type="email">
    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <input type="submit" >
</form>
</body>
</html>
