<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sat" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>${person.name} ${person.surname}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<c:set var="comments" scope="session" value="${person.comments}" />
<table>
    <tr>
        <td class="date">
            <c:out value="${person.birthDate}"/>
        </td>
    </tr>
    <tr>
        <td class="header">
            <h4><b><c:out value="${person.name} ${person.surname}"/></b></h4>
        </td>
    </tr>
    <tr>
        <td class="header">Description</td>
    </tr>
    <tr>
        <td><c:out value="${person.description}"/></td>
    </tr>
    <tr>
        <td>
            <table>
                <tr><td><sat:createComment action="${person.id}/comments/create"/></td></tr>
                <tr><td><sat:comments commented="${person}"/></td></tr>
            </table>
        </td>
        <td class="header">History</td>
    </tr>
</table>

</body>
</html>