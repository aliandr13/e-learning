<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <td>Id</td>
        <td>Last Name</td>
        <td>First Name</td>
        <td>Phone</td>
        <td>Action</td>
    </tr>
    <jsp:useBean id="students" scope="request" type="java.util.List"/>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.lastName}</td>
            <td>${student.firstName}</td>
            <td>${student.phone}</td>
            <td>
                <c:url value="/student-delete" var="delete_link">
                    <c:param name="id" value="${student.id}"/>
                </c:url>
                <a href="${delete_link}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
