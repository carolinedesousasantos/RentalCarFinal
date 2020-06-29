<%@page import="daoalugados.DaoAlugadosImpl"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="entidades.Veiculo"%>
<%@page import="daoveiculo.DaoVeiculoImpl"%>
<%@page import="entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// Senão estiver logado, redirecione.
    if (session.getAttribute("clientelogado") == null) {
        String erro = "Usuário não logado!";
        response.sendRedirect("index.jsp?erro=" + URLEncoder.encode(erro, "UTF-8"));
        // return para não prosseguir com o restante da página//Nesse caso funciona como break.
        return;
    }
%>
<!doctype html>
<html lang="pt-br">
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
        <link href="alugar.css" rel="stylesheet">
       
    </head>
    <body>
        <header>
            <div class="collapse bg-dark" id="navbarHeader">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4 offset-md-1 py-4">
                            <h4 class="text-white">Dados Pessoais</h4>
                            <ul class="list-unstyled">
                                <li><a href="#" class="text-white">Minha Conta</a></li>
                                <li><a href="logoff.jsp" class="text-white">Sair</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="navbar navbar-dark bg-dark box-shadow">
                <div class="container d-flex justify-content-between">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </div>
        </header>
        <main role="main">
            <section class="jumbotron text-center">
                <div class="container">
                    <%!
                        Cliente clienteAtual = null;
                        int idcliente;
                    %>
                    <h1 class="jumbotron-heading">
                        <%
                            clienteAtual = (Cliente) session.getAttribute("clientelogado");
                            idcliente = clienteAtual.getIdcliente();
                            out.print("Olá " + clienteAtual.getNombre() + " " + clienteAtual.getApellido() + "!");
                        %>
                    </h1>
                </div>
            </section>

            <div class="album py-5 bg-light">
                <div class="container">

                    <div class="row">
                        <%
                            DaoVeiculoImpl dao = new DaoVeiculoImpl();
                            DaoAlugadosImpl historico = new DaoAlugadosImpl();

                            for (Veiculo veiculo : dao.listaVeiculo()) {
                        %>
                        <div class="col-md-4 d-flex">

                            <div class="card mb-4 box-shadow">
                                <div class="card-header"><%= veiculo.getCategoria()%></div>
                                <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" src="<%=veiculo.getUrl()%>" alt="Card image cap">
                                <div class="card-body flex-fill">
                                    <p class="card-text"><%= veiculo.getInformation()%></p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <form action="AlugarServlet" method="post">
                                                <input type="hidden" name="idveiculo" value="<%=veiculo.getIdveiculo()%>">
                                                <input type="hidden" name="idcliente" value="<%=idcliente%>">
                                                <%if (veiculo.getStatus().equals("disponible")) { %>
                                                 <input type="submit" name="alugar" value="Alugar" class="btn btn-sm btn-outline-success">
                                                <% } else if (historico.podeDevolver(idcliente, veiculo.getIdveiculo()) == false) { %>
                                                Veículo indisponível!
                                                <% } else { %>
                                                <input type='submit' name='devolver' value='Devolver' class='btn btn-sm btn-outline-success' data-toggle="modal" data-target="#myModal">
                                                <% }%>
                                            </form>
                                        </div>
                                        <small class="text-muted">&euro; <%=veiculo.getPrice()%></small>
                                        <small class="text-muted"><%=veiculo.getModelo()%></small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }%>
                    </div>

                </div>
            </div>

        </main>

        <footer class="text-muted">
            <div class="container">
                <p class="float-right">
                    <a href="#">Back to top</a>
                </p>
            </div>
        </footer>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-slimScroll/1.3.8/jquery.slimscroll.min.js"><\/script>')</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.1/popper.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/holder/2.9.4/holder.min.js"></script>

            <%
            String msg = request.getParameter("msg");
            if(msg != null){ %>

            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <!--<div class="modal-header">
                            <h4 class="modal-title">Info</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>-->

                        <!-- Modal body -->
                        <div class="modal-body">
                            <%=msg %>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>

            <script>
            $('#myModal').modal();
            </script>
            <% } %>

    </body>
</html>
