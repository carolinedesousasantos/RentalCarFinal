package play;

import daoalugados.DaoAlugadosImpl;
import daocliente.DaoClienteImpl;
import daoveiculo.*;
import entidades.Aquilados;
import entidades.Cliente;
import entidades.Veiculo;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlugarVeiculoServlet", urlPatterns = {"/AlugarServlet"})
public class AlugarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("clientelogado") == null) {
            String erro = "Usuário não logado!";
            response.sendRedirect("index.jsp?erro=" + URLEncoder.encode(erro, "UTF-8"));
        } else {

            //Instancio o modelo dao para usar os métodos e atributos existentes.
            DaoVeiculoImpl daoVeiculo = new DaoVeiculoImpl();
            DaoClienteImpl daoCliente = new DaoClienteImpl();
            DaoAlugadosImpl daoAquilado = new DaoAlugadosImpl();

            //Recebe o id do cliente e veiculo.
            Integer idVeiculo = Integer.parseInt(request.getParameter("idveiculo"));
            Integer idcliente = Integer.parseInt(request.getParameter("idcliente"));

            Veiculo veiculo = daoVeiculo.encontrarVeiculoporId(idVeiculo);
            Cliente cliente = daoCliente.encontrarClienteporId(idcliente);

            String statusVeiculo;
            String statusAluguel;
            String msg;

            //Condição para mudar status no Banco de Dados.
            if (request.getParameter("alugar") != null && request.getParameter("alugar").equals("Alugar")) {
                statusVeiculo = "indisponible";
                statusAluguel = "alugado";
                msg = "Parabéns "+cliente.getNombre()+", seu veículo foi alugado com sucesso!";

            } else{
                if (daoAquilado.podeDevolver(idcliente, idVeiculo)==true) {
                    statusVeiculo = "disponible";
                    statusAluguel = "devolvido";
                    msg = "Obrigado(a) "+cliente.getNombre()+", até a próxima viagem!";
                } else {
                    //Não estou usando, pois quando o veículo está alugado por outro usuário, o botão devolver não aparece.
                    String e = "Acesso negado!";
                    throw new ServletException(e);
                }

            }

            Aquilados tabelaAlugado = new Aquilados();

            Date data = new Date();
            tabelaAlugado.setCliente(cliente);
            tabelaAlugado.setVeiculo(veiculo);
            tabelaAlugado.setFecha(data);
            tabelaAlugado.setStatus(statusAluguel);
            daoAquilado.create(tabelaAlugado);

            veiculo.setStatus(statusVeiculo);
            daoVeiculo.update(veiculo);

            response.sendRedirect("alugar.jsp?msg="  + URLEncoder.encode(msg, "UTF-8"));
        }
    }
}
