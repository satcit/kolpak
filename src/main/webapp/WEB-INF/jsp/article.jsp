<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sat" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>${article.name}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
<c:set var="comments" scope="session" value="${article.comments}" />
<table>
    <tr>
        <td class="date">
            <c:out value="Created: ${article.creationDate}"/>
        </td>
    </tr>
    <tr>
        <td class="date">
            <c:out value="Published: ${article.publicationDate}"/>
        </td>
    </tr>
    <tr>
        <td class="header">
            <h4><b><c:out value="${article.name}"/></b></h4>
        </td>
    </tr>
    <tr>
        <td>Authors:
            <c:forEach var="item" items="${article.authors}">
                <a href="/client/persons/${item.id}"><c:out value="${item.shortName}" /></a><c:out value=" " />
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td class="header">Description</td>
    </tr>
    <tr>
        <td><c:out value="${article.description}"/></td>
    </tr>
    <tr>
        <td>
            <table>
                <tr><td><sat:createComment action="${article.id}/comments/create"/></td></tr>
                <tr><td><sat:comments commented="${article}"/></td></tr>
            </table>
        </td>
        <%--TODO add history tag--%>
        <td class="header">History</td>
    </tr>
</table>

</body>
</html>