package servlets;

import dao.DaoClienteImpl;
import entities.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.ValidarResponse;
import user.PasswordValidator;
import util.JsonUtil;

@WebServlet(name = "ValidarContrasena", urlPatterns = {"/ValidarContrasena"})
public class ValidarContrasena extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            {
        String email = request.getParameter("email");
       String password = request.getParameter("password");
                String securePassword = PasswordValidator.encryptPassword(request,password);
        DaoClienteImpl daoCliente = new DaoClienteImpl();
        Cliente clienteValidado = daoCliente.validarEmailSenha(email, password);
        ValidarResponse validarResponse = new ValidarResponse();
        if (clienteValidado == null) {
            String msg = "Usuário ou senha inválidos.";
            validarResponse.setErro(true);
            validarResponse.setMsg(msg);
        } else {
            request.getSession().setAttribute("clientelogado", clienteValidado);
            validarResponse.setErro(false);
        }
        try {
            JsonUtil.jsonGenerator(response, validarResponse);
        } catch (IOException ex) {
            Logger.getLogger(ValidarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
