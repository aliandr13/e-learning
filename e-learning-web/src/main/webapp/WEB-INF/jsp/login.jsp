<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Login page">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.4/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.4/examples/sign-in/signin.css" rel="stylesheet">

</head>
<body class="text-center">

<p style="color: red;">${errorString}</p>

<form class="form-signin" method="POST" action="${pageContext.request.contextPath}/login">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="text" id="inputEmail" name="userName" class="form-control" placeholder="Email address" required
           autofocus value="${userName}">
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">
        Admin user: admin password: a_pass<br>
        Teacher user: teacher password: t_pass<br>
        Student user: student password: s_pass</p>

    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
</form>
</body>
</html>
