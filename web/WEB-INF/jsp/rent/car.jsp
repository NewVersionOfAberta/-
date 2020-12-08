<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cars catalog</title>
</head>
<body>

<ul>
    <c:forEach var="car" items="${cars}">
        <li>
            <form action="${pageContext.request.contextPath}/rent/rent-a-car?id=${car.id}" method="get">
                <c:out value="Марка: ${car.mark}" /><br/>
                <c:out value="Цвет: ${car.color}" /><br/>
                <c:out value="Цена: ${car.mark}" /><br/>
            <input type="button">
            </form>
        </li>
    </c:forEach>
</ul>


</body>
</html>