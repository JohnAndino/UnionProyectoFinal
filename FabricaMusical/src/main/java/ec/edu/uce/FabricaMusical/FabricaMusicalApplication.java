package ec.edu.uce.FabricaMusical;

import ec.edu.uce.FabricaMusical.controller.Contain;
import ec.edu.uce.FabricaMusical.models.EmailNotify;
import ec.edu.uce.FabricaMusical.models.Product;
import ec.edu.uce.FabricaMusical.models.interfaces.Notify;
import ec.edu.uce.FabricaMusical.view.MenuPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;


@SpringBootApplication
@ComponentScan({"ec.edu.uce", "ec.edu.uce.view", "ec.edu.uce.FabricaMusical.controller", "ec.edu.uce.FabricaMusical.services"})
public class FabricaMusicalApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(FabricaMusicalApplication.class, args);


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuPrincipal frame = context.getBean(MenuPrincipal.class);
                frame.setVisible(true);
            }
        });



    }



}
