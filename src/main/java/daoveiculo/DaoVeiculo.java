package daoveiculo;

import entidades.Veiculo;
import java.util.List;

public interface DaoVeiculo {

    public void create(Veiculo veiculo);

    public Veiculo read(Veiculo veiculo);

    public void update(Veiculo veiculo);

    public void delete(Veiculo veiculo);
    
    //public List<Veiculo> listaVeiculo();
}
