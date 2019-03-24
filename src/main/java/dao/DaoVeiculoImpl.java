package dao;

import entities.Veiculo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class DaoVeiculoImpl {

    private static Session session;
    private Transaction transaction;

    public DaoVeiculoImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void create(Veiculo veiculo) {
        try {
            this.transaction = session.beginTransaction();
            session.save(veiculo);
            this.transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    public Veiculo read(Veiculo veiculo) {
        Veiculo veiculoLido = (Veiculo) session.get(Veiculo.class, veiculo);
        return veiculoLido;
    }

    public void update(Veiculo veiculo) {
        try {
            this.transaction = session.beginTransaction();
            session.update(veiculo);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(Veiculo veiculo) {
        try {
            this.transaction = session.beginTransaction();
            session.delete(veiculo);
            transaction.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public List<Veiculo> listaVeiculo() {
        TypedQuery<Veiculo> consulta = session.createQuery("from Veiculo");
        return consulta.getResultList();
    }

    public Veiculo encontrarVeiculoporId(Integer idveiculo) {
        TypedQuery<Veiculo> consulta = session.createQuery("from Veiculo");
        for (Veiculo veiculo : consulta.getResultList()) {
            if (veiculo.getIdveiculo().equals(idveiculo)) {
                return veiculo;
            }
        }
        return null;
    }

}
