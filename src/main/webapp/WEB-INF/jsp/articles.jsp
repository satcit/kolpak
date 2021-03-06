<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Articles</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" >
</head>
<body>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Published</th>
        </tr>
    </thead>
    <tr>
        <td colspan="4">
            <form:form method="get" action="articles/create">
                <input type="submit" value="Create article" />
            </form:form>
        </td>
    </tr>
    <c:forEach items="${articles}" var="item">
        <tr>
            <td><a href="articles/${item.id}"><c:out value="${item.id}"/></a></td>
            <td><c:out value="${item.name}"/></td>
            <td>
                <c:choose>
                    <c:when test="${not empty item.authors}">
                        <c:set var="author" value="${item.authors[0]}" />
                        <a href="persons/${author.id}"><c:out value="${author.shortName}" /></a>
                    </c:when>
                    <c:otherwise>
                        no author
                    </c:otherwise>
                </c:choose>
            </td>
            <td><fmt:formatDate value="${item.publicationDate}" pattern="yyyy" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>