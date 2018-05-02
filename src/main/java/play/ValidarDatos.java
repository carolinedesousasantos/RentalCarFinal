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

@WebServlet(name = "ValidarDatos", urlPatterns = {"/ValidarDatos"})
public class ValidarDatos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cliente clienteDoFormulario = new Cliente();
        clienteDoFormulario.setNombre(request.getParameter("nombre"));
        clienteDoFormulario.setApellido(request.getParameter("apellido"));
        clienteDoFormulario.setEmail(request.getParameter("email"));
        clienteDoFormulario.setContrasena(request.getParameter("contrasena"));

        //A sessão já está no construtor do DaoClienteImpl
        DaoClienteImpl daoCliente = new DaoClienteImpl();

        //Crio uma variável do tipo Cliente e uso a var baseDatos para acessar ao método.
        //Se o resultado for null que dizer que o email não está cadastrado.
        Cliente verificarEmail = daoCliente.encontrarClienteporEmail(clienteDoFormulario.getEmail());
        if (verificarEmail == null) {
            daoCliente.create(clienteDoFormulario);      
            request.getSession().setAttribute("clientelogado", clienteDoFormulario);//Pega a sessão,coloca um nome e um cliente
            request.getRequestDispatcher("alugar.jsp").forward(request, response);
        } else {
            String msg = "Email já cadastrado.Tente outro email.";
            response.sendRedirect("registro.jsp?erro=" + URLEncoder.encode(msg, "UTF-8"));
        }
    }
}
