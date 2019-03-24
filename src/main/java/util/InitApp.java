
package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import servlets.CriarBD;


@WebListener
public class InitApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciou a sessão!");
        String[] args = new String[]{};
        CriarBD.main(args);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Derrubou a sessão!");
    }
    
}
