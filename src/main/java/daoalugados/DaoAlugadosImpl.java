package daoalugados;

import entidades.Aquilados;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.HibernateUtil;

import javax.persistence.TypedQuery;

public class DaoAlugadosImpl implements DaoAlugados {

    private static Session session;
    private Transaction tx;

    //Construtor abrindo a sessão
    public DaoAlugadosImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public Transaction getTx() {
        return tx;
    }

    public void create(Aquilados aquilado) {
        try {

            this.tx = session.beginTransaction();
            session.save(aquilado);
            tx.commit();
            System.out.println("Guardado com éxito");

        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            //Para web não é necessário usar
            //session.close();
            //HibernateUtil.getSessionFactory().close();
        }
    }

    public void update(Aquilados aquilado) {
        try {
            this.tx = session.beginTransaction();
            session.update(aquilado);
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

    //Devolve uma lista com os dados da tabela aquilados.
    public List<Aquilados> listaAquilados() {
        TypedQuery<Aquilados> consulta = session.createQuery("from Aquilados");
        return consulta.getResultList();

    }

    public void delete(Aquilados aquilado) {
        try {
            this.tx = session.beginTransaction();
            session.delete(aquilado);
            tx.commit();
            System.out.println("Apagado com éxito");
        } catch (HibernateException e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
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
}