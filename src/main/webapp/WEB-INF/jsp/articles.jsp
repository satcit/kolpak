<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
<table>
    <tr><th>ID</th><th>Name</th></tr>
    <c:forEach items="${articles}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><a href="articles/${item.id}"><c:out value="${item.name}"/></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>