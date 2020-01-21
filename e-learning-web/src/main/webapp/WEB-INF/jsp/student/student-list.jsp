<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="/WEB-INF/taglib/auth.tld" %>

<jsp:include page="../include/header.jsp"/>
<%@include file="../include/menu.jsp" %>


<table class="table table-striped">
    <thead>
    <th>#</th>
    <th>Last Name</th>
    <th>First Name</th>
    <th>Phone</th>
    <th>Group</th>
    <th>Action</th>

    </thead>
    <tbody>
    <jsp:useBean id="students" scope="request" type="java.util.List"/>
    <c:forEach var="student" items="${students}">
        <tr>
            <th scope="row">${student.id}</th>
            <th>${student.lastName}</th>
            <th>${student.firstName}</th>
            <th>${student.phone}</th>
            <th>
                <c:choose>
                    <c:when test="${student.group != null}">${student.group.name}</c:when>
                    <c:otherwise>Not Assigned</c:otherwise>
                </c:choose>
            </th>
            <th>
                <a:auth path="/user/student-delete">
                    <c:url value="/user/student-delete" var="delete_link" scope="page">
                        <c:param name="id" value="${student.id}"/>
                    </c:url>
                    <c:url value="/user/student-edit" var="edit_link" scope="page">
                        <c:param name="id" value="${student.id}"/>
                    </c:url>
                    <a href="${edit_link}">Edit</a> |
                    <a href="${delete_link}">Delete</a>
                </a:auth>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <c:url value="/user/student-add" var="add" scope="page"/>
    <a href="${add}">Add new Student</a>
</p>

<%@include file="../include/footer.jsp" %>
