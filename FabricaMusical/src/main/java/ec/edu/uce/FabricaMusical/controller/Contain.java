package ec.edu.uce.FabricaMusical.controller;

import ec.edu.uce.FabricaMusical.models.Product;
import ec.edu.uce.FabricaMusical.models.ProductConfig;
import ec.edu.uce.FabricaMusical.models.interfaces.Notify;
import ec.edu.uce.FabricaMusical.models.enums.Steps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

//@Component
public class Contain {
    private List<Product> products;
    private Notify notificationService;
    private ProductConfig productConfig;

    //@Autowired
    public Contain(Notify notificationService, String configFilePath) throws IOException {
        this.productConfig = new ProductConfig(configFilePath);
        this.products = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void startFabrication(Product product) {
        System.out.println("Iniciando fabricación de " + product.getName());
        Timer timer = new Timer();
        long delay = 0;

        for (Map.Entry<Steps, Integer> entry : product.getStepsOfFabrication().entrySet()) {
            Steps step = entry.getKey();
            int time = entry.getValue();

            delay += time * 1000;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Completando: " + step);
                }
            }, delay);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notificationService.notifyClient(product);
            }
        }, delay + 1000);
    }

    public void showProduct() {
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getMaterial() + " - " + product.getPrice());
            for (Map.Entry<Steps, Integer> entry : product.getStepsOfFabrication().entrySet()) {
                System.out.println("  " + entry.getKey() + " - Tiempo: " + entry.getValue() + " segundos");
            }
        }
    }

    public void createProducts() {
        for (ProductConfig.ProductData data : productConfig.getProducts()) {
            Product product = new Product(Math.toIntExact(data.getId()), data.getName(), data.getMaterial(), data.getPrice());
            for (Map.Entry<Steps, Integer> entry : data.getStepsOfFabrication().entrySet()) {
                product.addStepsOfFabrication(entry.getKey(), entry.getValue());
            }
            addProduct(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
