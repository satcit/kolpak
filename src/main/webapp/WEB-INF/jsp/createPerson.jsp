<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add person</title>
    <%--TODO add css--%>
</head>
<body>

<form:form action="create" modelAttribute="person" method="post">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="surname">Surname</form:label></td>
            <td><form:input path="surname" /></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:textarea path="description" /></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">BirthDate</form:label></td>
            <td><form:input path="birthDate" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Create" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>