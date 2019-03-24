package dao;

import entities.Cliente;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class DaoClienteImpl implements DaoCliente {

    private static Session session;
    private Transaction transaction;

    //Construtor abrindo a sessão
    public DaoClienteImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void create(Cliente cliente) {
        try {
            this.transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
            System.out.println("Guardado com éxito");

        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            //Para web não é necessário usar
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    @Override
    public void update(Cliente cliente) {
        try {
            this.transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    @Override
    public void delete(Cliente cliente) {
        try {
            this.transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Cliente> listaCliente() {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        return consulta.getResultList();

    }

    public Cliente encontrarClienteporEmail(String email) {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        return consulta.getResultList().stream().filter(c -> c.getEmail().equals(email)).findAny().orElse(null);
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

    public List<Cliente> read() {
        TypedQuery<Cliente> consulta = session.createQuery("from Cliente");
        return consulta.getResultList();

    }

    @Override
    public void read(Cliente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
