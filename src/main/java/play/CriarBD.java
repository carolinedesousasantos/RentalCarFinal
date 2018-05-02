package play;


import daocliente.DaoClienteImpl;
import daoveiculo.DaoVeiculoImpl;
import entidades.Cliente;
import entidades.Veiculo;


public class CriarBD {

    public static void main(String[] args) {

        //Cria novos veículos para a tabela veículos do Banco de Dados.
        DaoVeiculoImpl daoVeiculo = new DaoVeiculoImpl();
        if (daoVeiculo.listaVeiculo().isEmpty()) {

            /**
             * Fonte de imagens
             * https://www.localizahertz.com/brasil/pt-br/grupos-de-carros
             */
            daoVeiculo.create(new Veiculo(1, "Fiat", "Uno", "disponible", "img/NUNS.png", "A - ECONÔMICO (ECMN)", 50.f, "Características - Duas portas  Cinco pessoas  Duas mala(s) grande(s)  Uma mala(s) pequena(s) Sem Ar-condicionado Sem Dir. Hidráulica  Vidro elétrico"));
            daoVeiculo.create(new Veiculo(2, "Hyundai", "HB20", "disponible", "img/HB2X.png", "C - ECONÔMICO COM AR (EDMR)", 50.f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  1 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(3, "Ford", "Sedan", "disponible", "img/KASE.png", "F - INTERMEDIÁRIO", 60.0f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  1 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(4, "Citroen", "AirCross Start 1.6", "disponible", "img/AIRC.png", "M - SUV COMPACTO", 65.0f, "Características - Ar-condicionado  Dir. Hidráulica  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  2 mala(s) grande(s)  2 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(5, "Pegeout", "Suv", "disponible", "img/PEU2.png", "SUV COMPACTO AUTOMÁTICO", 70.0f, "Características: Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  ABS  Direção Elétrica  5 pessoas  2 mala(s) grande(s)  2 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(6, "Corolla", "GLI", "disponible", "img/CORO.png", "L-Executive", 80.0f, "Características: Ar-condicionado  Dir. Hidráulica  Automático  Vidro elétrico  Trava elétrica  4 portas  Air bag  ABS  5 pessoas  3 mala(s) grande(s)  2 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(7, "Fiat", "Doblo", "disponible", "img/DBLO.png", "R- MINIVAN", 60.0f, "Características: Ar-condicionado  Dir. Hidráulica  Vidro elétrico     Trava elétrica      3 portas     Air bag      ABS     7 pessoas"));
            daoVeiculo.create(new Veiculo(8, "Chevrolet", "GM Spin 1.8 ", "disponible", "img/SPIX.png", "RX - MINIVAN ESPECIAL", 70.0f, "Características: 4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Dir. Hidráulica  Automático  7 pessoas  2 mala(s) grande(s)"));
            daoVeiculo.create(new Veiculo(9, "Fiat", "Fiorino", "disponible", "img/FIFO.png", "U-FURGÃO", 60.0f, "Características:   2 portas    2 pessoas    620 KG    Sem Ar-Condicionado Sem Vidro elétrico Sem Dir. Hidráulica      Trava Elétrica"));
            daoVeiculo.create(new Veiculo(10, "Ford", "Ranger 2.2", "disponible", "img/RANG.png", "P - 4x4 ESPECIAL", 90.0f, "Características: Ar-condicionado  Dir. Hidráulica  Cabine Dupla  Tração 4x4  4 portas Banco de Couro  Diesel  Air bag  ABS  5 pessoas  "));
            daoVeiculo.create(new Veiculo(11, "Jaguar", " XF R-Sport", "disponible", "img/JXFR.png", "SP - SUPER PRIME", 120.0f, "Características: 4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  Banco de Couro  5 pessoas  3 mala(s) grande(s)  2 mala(s) pequena(s)"));
            daoVeiculo.create(new Veiculo(12, "Audi", "A4 Sedan 2.0", "disponible", "img/AUDL.png", "LP -PRIME", 110.0f, "Características :4 portas  Ar-condicionado  Vidro elétrico  Trava elétrica  Air bag  Automático  Banco de Couro  ABS  Direção Elétrica  5 pessoas  3 mala(s) grande(s)"));

            //HibernateUtil.getSessionFactory().close();
        }
        
        DaoClienteImpl daoCliente = new DaoClienteImpl();
        if (daoCliente.listaCliente().isEmpty()) {
            daoCliente.create(new Cliente("Caroline", "Santos", "carol@carol", "123"));
        }
    }
}
