package ec.edu.uce.FabricaMusical.controller;

import ec.edu.uce.FabricaMusical.models.Product;
import ec.edu.uce.FabricaMusical.models.interfaces.Notify;
import ec.edu.uce.FabricaMusical.models.enums.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Container {
    private List<Product> products;
    private Notify notificationService;

    public Container(Notify notificationService) {
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

    public List<Product> getProducts() {
        return products;
    }
}
