<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="action" required="true" %>

<form:form action="${action}" modelAttribute="comment" method="post">
    <table>
        <tr>
            <td><form:textarea path="text" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Comment" /></td>
        </tr>
        <tr><td></td></tr>
    </table>
</form:form>