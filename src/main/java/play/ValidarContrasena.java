package play;

import daocliente.DaoClienteImpl;
import entidades.Cliente;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidarContrasena", urlPatterns = {"/ValidarContrasena"})
public class ValidarContrasena extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        DaoClienteImpl daoCliente = new DaoClienteImpl();
        Cliente clienteValidado = daoCliente.validarEmailSenha(email, contrasena);
        if (clienteValidado == null) {
            String msg = "Usuário ou senha inválidos.";
            response.sendRedirect("index.jsp?erro=" + URLEncoder.encode(msg, "UTF-8"));
        } else {
            request.getSession().setAttribute("clientelogado", clienteValidado);
            request.getRequestDispatcher("alugar.jsp").forward(request, response);
        }
    }

}
