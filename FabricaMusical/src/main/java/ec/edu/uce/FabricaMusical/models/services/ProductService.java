package ec.edu.uce.FabricaMusical.models.services;

import ec.edu.uce.FabricaMusical.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> selectedProducts = new ArrayList<>();

    public void addProduct(Product product) {
        selectedProducts.add(product);
    }

    public List<Product> getSelectedProducts() {
        return new ArrayList<>(selectedProducts);
    }

    public void clearSelectedProducts() {
        selectedProducts.clear();
    }

    public List<Product> findAll() {
        return new ArrayList<>(selectedProducts);
    }
}
