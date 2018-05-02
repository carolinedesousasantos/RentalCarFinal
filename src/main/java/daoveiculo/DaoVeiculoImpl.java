package daoveiculo;

import entidades.Veiculo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.HibernateUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class DaoVeiculoImpl {

    private static Session session;
    private Transaction tx;

    public DaoVeiculoImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void create(Veiculo veiculo) {
        try {
            this.tx = session.beginTransaction();
            session.save(veiculo);
            this.tx.commit();

            System.out.println("Guardado com éxito");

        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            //Para web não é necessário usar
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    /* public Veiculo read(Veiculo veiculo) {
        Veiculo veiculoLido = (Veiculo) session.get(Veiculo.class, veiculo);
        return veiculoLido;
    }*/
    public void update(Veiculo veiculo) {
        try {
            this.tx = session.beginTransaction();
            session.update(veiculo);
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

    public void delete(Veiculo veiculo) {
        try {
            this.tx = session.beginTransaction();
            session.delete(veiculo);
            tx.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Devolve uma lista de veíclos
    public List<Veiculo> listaVeiculo() {
        TypedQuery<Veiculo> consulta = session.createQuery("from Veiculo");
        return consulta.getResultList();
    }

    public Veiculo encontrarVeiculoporId(Integer idveiculo) {
        TypedQuery<Veiculo> consulta = session.createQuery("from Veiculo");
        for (Veiculo c : consulta.getResultList()) {
            if (c.getIdveiculo().equals(idveiculo)) {
                return c;
            }
        }
        return null;
    }

}
