 <%@include file="../include/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/admin/user-add">
    <table>
        <tr>
            <td><label>First Name</label></td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td><label>Middle Name</label></td>
            <td><input type="text" name="middleName"/></td>
        </tr>
        <tr>
            <td><label>Last Name</label></td>
            <td><input type="text" name="surname"/></td>
        </tr>
        <tr>
            <td><label>Phone</label></td>
            <td><input type="text" name="phone"/></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td><label for="course">Course</label></td>
            <td>
                <select id="course" name="courseId">
                    <option value="-1"></option>
                    <c:forEach var="group" items="${groups}">
                        <option value="${group.id}">${group.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="../include/footer.jsp" %>
