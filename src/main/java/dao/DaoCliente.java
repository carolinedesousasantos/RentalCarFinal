package dao;

import entities.Cliente;
import java.util.List;

public interface DaoCliente extends DaoGeneric<Cliente>{
    public List<Cliente> listaCliente();

    public Cliente encontrarClienteporEmail(String email);

    public Cliente validarEmailSenha(String email, String contrasena);
    
}
