package daocliente;

import entidades.Cliente;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.HibernateUtil;

public class DaoClienteImpl implements DaoCliente {

    private static Session session;
    private Transaction tx;

    //Construtor abrindo a sessão
    public DaoClienteImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public Transaction getTx() {
        return tx;
    }

    public void create(Cliente cliente) {
        try {
            this.tx = session.beginTransaction();
            session.save(cliente);
            tx.commit();
            System.out.println("Guardado com éxito");

        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            //Para web não é necessário usar
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    public void update(Cliente cliente) {
        try {
            this.tx = session.beginTransaction();
            session.update(cliente);
            tx.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    public void delete(Cliente cliente) {
        try {
            this.tx = session.beginTransaction();
            session.delete(cliente);
            tx.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Devolve a lista de clientes.Se quiser filtrar podemos usar o get
    public List<Cliente> listaCliente() {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        return consulta.getResultList();

    }

    //Encontra o email.
    public Cliente encontrarClienteporEmail(String email) {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        for (Cliente c : consulta.getResultList()) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public Cliente encontrarClienteporId(Integer idcliente) {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        for (Cliente c : consulta.getResultList()) {
            if (c.getIdcliente().equals(idcliente)) {
                return c;
            }
        }
        return null;
    }

    //Verifica se a senha e o email estão corretos.
    public Cliente validarEmailSenha(String email, String contrasena) {
       TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        for (Cliente c : consulta.getResultList()) {          
            if (c.getEmail().equals(email) && c.getContrasena().equals(contrasena)) {
                return c;
            }
        }
        return null;
    }
    
    
     public List<Cliente>  listaCliente2() {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        return consulta.getResultList();

    }

}
