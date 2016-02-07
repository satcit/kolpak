<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css">
</head>
<body>

<form:form action="update" modelAttribute="articleBean" method="post">
    <table>
        <tr>
            <td><form:label path="article.name">Name</form:label></td>
            <td><form:input path="article.name" /></td>
        </tr>
        <tr>
            <td><form:label path="article.description">Description</form:label></td>
            <td><form:textarea path="article.description" /></td>
        </tr>
        <tr></tr>
        <%--TODO make file upload--%>
        <%--<tr>--%>
            <%--<td><form:label path="fileLink">File</form:label></td>--%>
            <%--<td><input type="file" name="file" /> </td>--%>
        <%--</tr>--%>
        <tr>
            <%--<td><form:label path="authros" />Author</td>--%>
            <td>Author</td>
            <td>
                <form:select multiple="true" path="article.authors" items="${allAuthors}" itemValue="id" itemLabel="shortName" id="createArticle_authors"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="article.publicationDate">Published</form:label></td>
            <td><form:input path="article.publicationDate" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="${articleBean.action}" />
                <form:hidden path="action" />
                <form:hidden path="article.id" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>