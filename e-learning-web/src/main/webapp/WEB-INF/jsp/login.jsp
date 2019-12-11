<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link href="<c:url value="/css/signin.css"/>" rel="stylesheet">

    <title>Login</title>

    <fmt:setLocale value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>

</head>
<body class="text-center">

<form class="form-signin" method="post" action="<c:url value="/login"/>">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <c:if test="${not empty errorString}">
        <div class="alert alert-danger align-items-center" role="alert">${errorString}</div>
    </c:if>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required
           autofocus value="${email}">
    <label for="inputPassword" class="sr-only"><fmt:message key="login.page.user.password"/></label>
    <input type="password" id="inputPassword" name="password" class="form-control"
           placeholder="<fmt:message key="login.page.user.password"/>" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="remember" value="Y">Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; E-Learning 2019</p>
</form>

<script src="<c:url value="/js/jquery-3.4.1.slim.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>
