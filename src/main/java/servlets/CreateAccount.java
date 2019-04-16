package servlets;

import dao.DaoClienteImpl;
import entities.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.ValidarResponse;
import user.PasswordValidator;
import util.JsonUtil;

@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg;
        boolean error = false;
        ValidarResponse signinResponse = new ValidarResponse();
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("name"));
        cliente.setApellido(request.getParameter("surname"));
        cliente.setEmail(request.getParameter("email"));
        boolean passwordValidate = PasswordValidator.validate(request.getParameter("password"));

        if (passwordValidate) {
            msg = PasswordValidator.checkPasswords(request.getParameter("password"),
                    request.getParameter("confirmPassword"));
            if (msg.equals("")) {
                String password = request.getParameter("password");
                String securePassword = PasswordValidator.encryptPassword(request, password);
                cliente.setContrasena(securePassword);
                validarEmail(cliente, request, signinResponse);
            } else {
                signinResponse.setMsg(msg);
                signinResponse.setErro(true);
            }

        } else {
            msg = "La contraseña debe tener minimo de 8 caracteres de longitud y combinación de números,"
                    + " letras por lo menos una letra maiuscúla.";
            signinResponse.setMsg(msg);
            signinResponse.setErro(true);
        }

        JsonUtil.jsonGenerator(response, signinResponse);

    }

    public void validarEmail(Cliente cliente, HttpServletRequest request, ValidarResponse signinResponse) {
        DaoClienteImpl daoCliente = new DaoClienteImpl();
        Cliente verificarEmail = daoCliente.encontrarClienteporEmail(cliente.getEmail());
        if (verificarEmail == null) {
            daoCliente.create(cliente);
            request.getSession().setAttribute("clientelogado", cliente);
            signinResponse.setErro(false);
        } else {
            signinResponse.setMsg("Email já cadastrado.Tente outro email.");
            signinResponse.setErro(true);
        }
    }
}
