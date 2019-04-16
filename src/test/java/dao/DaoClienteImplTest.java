package dao;

import entities.Cliente;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.HibernateUtil;

public class DaoClienteImplTest {

    Session session;
    Transaction transaction;

    public DaoClienteImplTest() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   // @Test
    public void create() {
        this.transaction = session.beginTransaction();
        Cliente cliente = new Cliente();
        try {
            cliente.setNombre("Humberto");
            cliente.setApellido("Dias");
            cliente.setEmail("beto@beto");
            cliente.setContrasena("A12345678");
            session.save(cliente);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            transaction.rollback();
            e.getMessage();
            System.out.println("Não foi possível guardar as informações do cliente");
        } finally {
            TypedQuery<Cliente> consulta = session.createQuery("from Cliente where idCliente=" + cliente.getIdcliente());
            System.out.println(consulta.getResultList());
            Assert.assertEquals(1, consulta.getResultList().size());
        }

    }

    //@Test
    public void update() {
        this.transaction = session.beginTransaction();
        Cliente cliente = new Cliente();
        try {
            cliente.setNombre("Humberto");
            cliente.setApellido("Dias");
            cliente.setEmail("beto@beto");
            cliente.setContrasena("A12345678");
            session.save(cliente);
            cliente.setApellido("Dos Santos Dos Santos");
            session.update(cliente);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
            System.out.println("Não foi possível alterar as informações do cliente");
        } finally {
            TypedQuery<Cliente> consulta = session.createQuery("from Cliente where idCliente=" + cliente.getIdcliente());
            System.out.println(consulta.getResultList());
            Cliente resultado = consulta.getResultList().get(0);
            Assert.assertEquals("Dos Santos Dos Santos", resultado.getApellido());
        }
    }

   // @Test
    public void delete() {
        this.transaction = session.beginTransaction();
        Cliente cliente = new Cliente();
        try {
            cliente.setNombre("Humberto");
            cliente.setApellido("Dias");
            cliente.setEmail("beto@beto");
            cliente.setContrasena("A12345678");
            session.save(cliente);
            session.delete(cliente);
            transaction.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
            System.out.println("Não foi possível apagar as informações do cliente");
        } finally {
            TypedQuery<Cliente> consulta = session.createQuery("from Cliente where idCliente=" + cliente.getIdcliente());
            System.out.println(consulta.getResultList());
            Cliente resultado = consulta.getResultList().get(0);
           // Assert.assertEquals("Dos Santos Dos Santos", resultado.getApellido());
        }
    }
}
