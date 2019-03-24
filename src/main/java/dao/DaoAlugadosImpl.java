package dao;

import entities.Aquilados;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.TypedQuery;

public class DaoAlugadosImpl implements DaoAlugados {

    private static Session session;
    private Transaction transaction;

    public DaoAlugadosImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void create(Aquilados aquilado) {
        try {
            this.transaction = session.beginTransaction();
            session.save(aquilado);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Aquilados aquilado) {
        try {
            this.transaction = session.beginTransaction();
            session.update(aquilado);
            transaction.commit();
            System.out.println("Guardado com éxito");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    public List<Aquilados> listaAquilados() {
        TypedQuery<Aquilados> consulta = session.createQuery("from Aquilados");
        return consulta.getResultList();
    }

    @Override
    public void delete(Aquilados aquilado) {
        try {
            this.transaction = session.beginTransaction();
            session.delete(aquilado);
            transaction.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public boolean podeDevolver(Integer idcliente, Integer idVeiculo) {
        Boolean podeDevolver = false;
        List<Aquilados> lista = listaAquilados();
        for (Aquilados item : lista) {
            if (item.getCliente().getIdcliente() == idcliente
                    && item.getVeiculo().getIdveiculo() == idVeiculo
                    && item.getVeiculo().getStatus().equals("indisponible")) {
                podeDevolver = true;
                break;
            }
        }
        return podeDevolver;
    }

    @Override
    public void read(Aquilados t) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }
}
