<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/student-add">
    <label>First Name
        <input type="text" name="firstName"/>
    </label>
    <label>Middle Name
        <input type="text" name="middleName"/>
    </label>
    <label>Last Name
        <input type="text" name="lastName"/>
    </label>
    <label>Phone
        <input type="tel" name="phone"/>
    </label>
    <input type="submit">
</form>
</body>
</html>
