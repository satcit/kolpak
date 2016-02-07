<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css">
</head>
<body>

<form:form action="update" modelAttribute="personBean" method="post">
    <table>
        <tr>
            <td><form:label path="person.name">Name</form:label></td>
            <td><form:input path="person.name" /></td>
        </tr>
        <tr>
            <td><form:label path="person.surname">Surname</form:label></td>
            <td><form:input path="person.surname" /></td>
        </tr>
        <tr>
            <td><form:label path="person.description">Description</form:label></td>
            <td><form:textarea path="person.description" /></td>
        </tr>
        <tr>
            <td><form:label path="person.birthDate">BirthDate</form:label></td>
            <td><form:input path="person.birthDate" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="${personBean.action}" />
                <form:hidden path="action" />
                <form:hidden path="person.id" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>