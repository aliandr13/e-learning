<%@ page import="static by.it.academy.elearning.web.servlet.student.StudentListServlet.GROUP_ID_PARAM" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="/WEB-INF/taglib/auth.tld" %>

<jsp:include page="../include/header.jsp"/>
<%@include file="../include/menu.jsp" %>


<table class="table table-striped" border="1">
    <thead>
    <th>#</th>
    <th>Group Name</th>
    <th>Course Name</th>
    <th>Start date</th>
    <th>Students</th>
    <th>Delete</th>

    </thead>
    <tbody>
    <jsp:useBean id="groups" scope="request" type="java.util.List"/>
    <c:forEach var="group" items="${groups}">
        <tr>
            <th scope="row">${group.id}</th>
            <th>${group.name}</th>
            <th>${group.course.name}</th>
            <th>${group.startDate}</th>
            <th>
                <c:url value="/user/student-list" var="students_link" scope="page">
                    <c:param name="<%= GROUP_ID_PARAM %>" value="${group.id}"/>
                </c:url>
                <a href="${students_link}">View Students</a>
            </th>
            <th>
                <a:auth path="/user/student-delete">
                    <c:url value="/user/group-delete" var="delete_link" scope="page">
                        <c:param name="id" value="${group.id}"/>
                    </c:url>
                    <a href="${delete_link}">Delete</a>
                </a:auth>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <c:url value="/user/group-add" var="add" scope="page"/>
    <a href="${add}">Add new Group</a>
</p>

<%@include file="../include/footer.jsp" %>
