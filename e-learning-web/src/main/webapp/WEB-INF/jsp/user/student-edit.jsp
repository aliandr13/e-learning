<jsp:useBean id="student" scope="request" type="by.it.academy.elearning.model.Student"/>
<%@include file="../include/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/user/student-edit">
    <input type="hidden" name="student_id" value="${student_id}"/>
    <table>
        <tr>
            <td><label>First Name</label></td>
            <td><input type="text" name="firstName" value="${student.firstName}"/></td>
        </tr>
        <tr>
            <td><label>Middle Name</label></td>
            <td><input type="text" name="middleName" value="${student.middleName}"/></td>
        </tr>
        <tr>
            <td><label>Last Name</label></td>
            <td><input type="text" name="lastName" value="${student.lastName}"/></td>
        </tr>
        <tr>
            <td><label>Phone</label></td>
            <td><input type="text" name="phone" value="${student.phone}"/></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="text" name="email" value="${student.email}"/></td>
        </tr>
        <tr>
            <td><label for="course">Course</label></td>
            <td>
                <select id="course" name="courseId">
                    <option value="-1">Not Selected</option>
                    <c:forEach var="group" items="${groups}">
                        <option value="${group.id}"
                                <c:if test="${groupSelected == group.id}">selected='selected'</c:if>
                        >${group.name}</option>
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
