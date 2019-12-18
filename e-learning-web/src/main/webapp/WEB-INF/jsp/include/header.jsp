<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1 style="margin-top: 10px"><fmt:message key="header.app.title"/></h1>
    </div>

    <c:out value="${sessionScope.elearning_locale}"/>

    <div style="float: right; padding: 15px; text-align: right;">
        <!-- User store in session with attribute: loginedUser -->
        <c:if test="${sessionScope.auth_user != null}"> Hello <b>${sessionScope.auth_user.userName}</b>
            <a href="${pageContext.request.contextPath}/logout">logout</a>
        </c:if>
    </div>
</div>
