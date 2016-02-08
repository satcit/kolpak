<%@attribute name="commented" required="true" type="ru.satcit.kolpak.model.Commented" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sat" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
    <tr>
        <td class="header">Comments</td>
    </tr>
    <c:forEach var="item" items="${commented.comments}">
        <tr>
            <td class="date"><fmt:formatDate value="${item.date}" pattern="dd-MM-yyyy HH:mm" /></td>
        </tr>
        <tr>
            <td><c:out value="${item.text}"/></td>
        </tr>
    </c:forEach>
</table>
