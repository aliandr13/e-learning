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

                <a:auth path="/student-delete">
                    <a href="${delete_link}">Delete</a>
                </a:auth>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <c:url value="/student-add" var="add" scope="page"/>
    <a href="${add}">Add new Student</a>
</p>

<%@include file="../include/footer.jsp" %>
