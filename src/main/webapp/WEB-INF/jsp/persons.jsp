<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Persons</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
<table>
    <tr><th>ID</th><th>Name</th><th>Surname</th><th>Birth date</th></tr>
    <c:forEach items="${persons}" var="item">
        <tr>
            <td><a href="persons/${item.id}"><c:out value="${item.id}"/></a></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.surname}"/></td>
            <td><c:out value="${item.birthDate}"/></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2">
            <form:form method="get" action="persons/create">
                <input type="submit" value="Add person" />
            </form:form>
        </td>
    </tr>
</table>
</body>
</html>