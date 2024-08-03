package ec.edu.uce.FabricaMusical;

import ec.edu.uce.FabricaMusical.controller.Container;
import ec.edu.uce.FabricaMusical.models.*;
import ec.edu.uce.FabricaMusical.models.interfaces.Notify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


@SpringBootApplication(scanBasePackages = {"ec.edu.uce.FabricaMusical", "ec.edu.uce.FabricaMusical.models.services", "resources"})
public class FabricaMusicalApplication {

	public static void main(String[] args) {
		//System.setProperty("java.awt.headless", "false");
		//SpringApplication.run(FabricaMusicalApplication.class, args);

		try {
			Notify notificationService = new EmailNotify();
			Container container = new Container(notificationService);
			ProductFactory productFactory = new ProductFactory(container, "src/main/resources/productConfig.json");

			// Crear y agregar productos al contenedor usando la fábrica
			productFactory.createProducts();

			// Mostrar productos
			container.showProduct();

			// Iniciar fabricación de un producto
			Product guitarra = container.getProducts().get(0);
			container.startFabrication(guitarra);
			Product saxo = container.getProducts().get(1);
			container.startFabrication(saxo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
