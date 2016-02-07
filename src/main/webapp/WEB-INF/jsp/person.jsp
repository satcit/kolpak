<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sat" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>${person.name} ${person.surname}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<table>
    <tr>
        <td><a href="<c:url value="${person.id}/edit"/>">Edit</a></td>
        <td class="date">
            <c:out value="${person.birthDate}"/>
        </td>
    </tr>
    <tr>
        <td class="header" colspan="2">
            <h4><b><c:out value="${person.name} ${person.surname}"/></b></h4>
        </td>
    </tr>
    <tr>
        <td class="header" colspan="2">Description</td>
    </tr>
    <tr>
        <td colspan="2"><c:out value="${person.description}"/></td>
    </tr>
    <tr>
        <td>
            <table id="person_commentsTable">
                <tr><td><sat:createComment action="${person.id}/comments/create"/></td></tr>
                <tr><td><sat:comments commented="${person}"/></td></tr>
            </table>
        </td>
        <td class="header">History</td>
    </tr>
</table>

</body>
</html>