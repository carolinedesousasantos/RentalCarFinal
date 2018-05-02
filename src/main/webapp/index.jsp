<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="favicon.png">

        <title>Aluguel de Veículos</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="registro.css" rel="stylesheet">
    </head>

    <body class="text-center">
        <form class="form-signin" action="ValidarContrasena" method="post">
            <img class="mb-4" src="logo.png" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Login</h1>
            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
            <label for="inputPassword" class="sr-only">Senha</label>
            <input type="password"  name="contrasena" id="inputPassword" class="form-control" placeholder="Password" required autofocus>
            <%
                String msg = request.getParameter("erro");
                if (msg != null) {
                    out.print(msg);
                }
            %>
            <button class="btn btn-lg btn-success btn-block" type="submit">Entrar</button>
            <a href="registro.jsp">Registrar-se</a>
            <p class="mt-5 mb-3 text-muted">&copy;Localiza 2018</p>
        </form>
    </body>
</html>
