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
    <th>email</th>
    <th>Action</th>

    </thead>
    <tbody>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach var="user" items="${users}">
        <tr>
            <th scope="row">${user.id}</th>
            <th>${user.userInfo.lastName}</th>
            <th>${user.userInfo.firstName}</th>
            <th>${user.userInfo.phone}</th>
            <th>${user.email}</th>

            <th>
                <a:auth path="/user/user-delete">
                    <c:url value="/user/user-delete" var="delete_link" scope="page">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <c:url value="/user/user-edit" var="edit_link" scope="page">
                        <c:param name="id" value="${user.id}"/>
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
    <c:url value="/user/user-add" var="add" scope="page"/>
    <a href="${add}">Add new user</a>
</p>

<%@include file="../include/footer.jsp" %>
