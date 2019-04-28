package servlets;

import dao.DaoVeiculoImpl;
import dao.DaoAlugadosImpl;
import dao.DaoClienteImpl;
import entities.Aquilados;
import entities.Cliente;
import entities.Veiculo;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.ValidarResponse;
import util.JsonUtil;

@WebServlet(name = "AlugarVeiculoServlet", urlPatterns = {"/AlugarServlet"})
public class AlugarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ValidarResponse validarResponse = new ValidarResponse();
        SessionServlet sessionServlet = new SessionServlet();
        String statusAluguel = null;
        String statusVeiculo;
        String msg;


        if (request.getSession().getAttribute("clientelogado") == null) {

            msg = "Usuário não logado!";
            validarResponse.setErro(true);
            validarResponse.setMsg(msg);
            //validarResponse.setSession(sessionServlet);

        } else {
            DaoVeiculoImpl daoVeiculo = new DaoVeiculoImpl();
            DaoClienteImpl daoCliente = new DaoClienteImpl();
            DaoAlugadosImpl daoAquilado = new DaoAlugadosImpl();

            Integer idVeiculo = Integer.parseInt(request.getParameter("idveiculo"));
            Integer idcliente = Integer.parseInt(request.getParameter("idcliente"));
            Veiculo veiculo = daoVeiculo.encontrarVeiculoporId(idVeiculo);
            Cliente cliente = daoCliente.encontrarClienteporId(idcliente);

            if (request.getParameter("status").equalsIgnoreCase("Alugar")) {
                statusVeiculo = "indisponible";
                statusAluguel = "alugado";
                msg = "Parabéns " + cliente.getNombre() + ", seu veículo foi alugado com sucesso!";

            } else {
                if (daoAquilado.podeDevolver(idcliente, idVeiculo) == true) {
                    statusVeiculo = "disponible";
                    statusAluguel = "devolvido";
                    msg = "Obrigado(a) " + cliente.getNombre() + ", até a próxima viagem!";
                } else{
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
            validarResponse.setErro(false);
        }

        validarResponse.setErro(false);
        //validarResponse.setSession(sessionServlet);
        validarResponse.setStatusAluguel(statusAluguel);
        validarResponse.setMsg(msg);

        try {
            JsonUtil.jsonGenerator(response, validarResponse);
        } catch (IOException ex) {
            Logger.getLogger(ValidarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
