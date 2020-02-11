<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="/WEB-INF/taglib/auth.tld" %>

<jsp:include page="../include/header.jsp"/>
<%@include file="../include/menu.jsp" %>


<table class="table table-striped">
    <thead>
    <th>#</th>
    <th>Name</th>
    <th>Action</th>

    </thead>
    <tbody>
    <jsp:useBean id="courses" scope="request" type="java.util.List"/>
    <c:forEach var="course" items="${courses}">
        <tr>
            <th scope="row">${course.id}</th>
            <th>${course.name}</th>
            <th>
                    <%--                <a:auth path="/admin/user-delete">--%>
                <c:url value="/admin/course-delete" var="delete_link" scope="page">
                    <c:param name="id" value="${course.id}"/>
                </c:url>
                <c:url value="/admin/course-edit" var="edit_link" scope="page">
                    <c:param name="id" value="${course.id}"/>
                </c:url>
                <a href="${edit_link}">Edit</a> |
                <a href="${delete_link}">Delete</a>
                    <%--                </a:auth>--%>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <c:url value="/admin/course-add" var="add" scope="page"/>
    <a href="${add}">Add new course</a>
</p>

<%@include file="../include/footer.jsp" %>
