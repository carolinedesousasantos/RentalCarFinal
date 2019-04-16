package servlets;

import entities.Cliente;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;

@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, String> sessioInfo = new HashMap<>();

        Enumeration<String> session = request.getSession().getAttributeNames();
        while (session.hasMoreElements()) {
            String atributeName = session.nextElement();
            Object atributeValue = request.getSession().getAttribute(atributeName);
            if (atributeValue != null) {
                if (atributeValue instanceof Cliente) {
                    Cliente cliente = (Cliente) atributeValue;
                    sessioInfo.put("name", cliente.getNombre());
                    sessioInfo.put("surname", cliente.getApellido());
                    sessioInfo.put("id", cliente.getIdcliente().toString());
                } else {
                    sessioInfo.put(atributeName, atributeValue.toString());
                }
            }
        }
        try {
            JsonUtil.jsonGenerator(response, sessioInfo);
        } catch (IOException ex) {
            Logger.getLogger(ValidarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
