<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sat" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="math" uri="http://satcit.com/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${article.name}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<table>
    <tr>
        <td><a href="<c:url value="${article.id}/edit"/>">Edit</a></td>
        <td class="date">
            <c:out value="Created: "/>
            <fmt:formatDate value="${article.creationDate}" pattern="dd-MM-yyyy" />
        </td>
    </tr>
    <tr>
        <td class="date" colspan="2">
            <c:out value="Published: "/>
            <fmt:formatDate value="${article.publicationDate}" pattern="MM-yyyy" />
        </td>
    </tr>
    <tr>
        <td class="header" colspan="2">
            <h4><b><c:out value="${article.name}"/></b></h4>
        </td>
    </tr>
    <tr>
        <td colspan="2">Authors:
            <c:forEach var="item" items="${article.authors}">
                <a href="../persons/${item.id}"><c:out value="${item.shortName}" /></a><c:out value=" " />
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td class="header" colspan="2">Description</td>
    </tr>
    <tr>
        <td colspan="2"><c:out value="${article.description}"/></td>
    </tr>
    <tr>
        <td colspan="2"><form:form action="${article.id}/upload" method="post" enctype="multipart/form-data">
            <table><tr>
                <%--TODO fix file opening--%>
                <td><a href="${article.file.path}">${article.file.name}</a></td>
                <td><input type="file" name="file" /></td>
                <td><input type="submit" value="Upload" /></td>
            </tr></table>
        </form:form></td>
    </tr>
    <tr>
        <td>
            <table>
                <tr><td><sat:createComment action="${article.id}/comments/create"/></td></tr>
                <tr><td><sat:comments commented="${article}"/></td></tr>
            </table>
        </td>
        <td>
            <table>
                <tr><td>History</td></tr>
                <c:forEach items="${article.history}" var="event" end="${math:min(fn:length(article.history), historyLimit)}">
                    <tr><td class="date"><fmt:formatDate value="${event.date}" pattern="dd-MM-yyyy HH:mm" /></td></tr>
                    <tr><td>${event.action}</td></tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>