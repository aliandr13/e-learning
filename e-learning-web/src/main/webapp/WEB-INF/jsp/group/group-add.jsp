<%@include file="../include/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/user/group-add">
    <table>
        <tr>
            <td><label>First Name</label></td>
            <td><input type="text" name="groupName"/></td>
        </tr>
        <tr>
            <td><label>Start Date</label></td>
            <td><input type="date" name="startDate"/></td>
        </tr>
        <tr>
            <td><label for="course">Course</label></td>
            <td>
                <select id="course" name="courseId">
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.id}"
                                <c:if test="${courseSelected == course.id}">selected='selected'</c:if>  >${course.name}</option>
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
