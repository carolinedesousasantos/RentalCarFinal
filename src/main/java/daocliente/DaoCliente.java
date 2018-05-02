package daocliente;

import entidades.Cliente;
import java.util.List;

public interface DaoCliente {

    public void create(Cliente cliente);

    public void update(Cliente cliente);

    public void delete(Cliente idcliente);

    public List<Cliente> listaCliente();

    public Cliente encontrarClienteporEmail(String email);

    public Cliente validarEmailSenha(String email, String contrasena);
}
