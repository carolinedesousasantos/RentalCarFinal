package daoalugados;

import entidades.Aquilados;
import java.util.List;

public interface DaoAlugados {

    public void create(Aquilados aquilado);

    public void update(Aquilados aquilado);

    public List<Aquilados> listaAquilados();

    public void delete(Aquilados aquilado);
}
