package ec.edu.uce.FabricaMusical.models;

import ec.edu.uce.FabricaMusical.controller.Container;
import ec.edu.uce.FabricaMusical.models.enums.Steps;

import java.io.IOException;
import java.util.Map;

public class ProductFactory {
    private Container container;
    private ProductConfig productConfig;

    public ProductFactory(Container container, String configFilePath) throws IOException {
        this.container = container;
        this.productConfig = new ProductConfig(configFilePath);
    }

    public void createProducts() {
        for (ProductConfig.ProductData data : productConfig.getProducts()) {
            Product product = new Product(data.getId(), data.getName(), data.getMaterial(), data.getPrice());
            for (Map.Entry<Steps, Integer> entry : data.getStepsOfFabrication().entrySet()) {
                product.addStepsOfFabrication(entry.getKey(), entry.getValue());
            }
            container.addProduct(product);
        }
    }
}


