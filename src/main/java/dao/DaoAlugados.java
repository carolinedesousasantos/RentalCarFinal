package dao;

import entities.Aquilados;
import java.util.List;

public interface DaoAlugados extends DaoGeneric<Aquilados> {

    public List<Aquilados> listaAquilados();
}
