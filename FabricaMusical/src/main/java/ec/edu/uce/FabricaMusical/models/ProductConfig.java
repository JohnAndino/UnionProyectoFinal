package ec.edu.uce.FabricaMusical.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ec.edu.uce.FabricaMusical.models.enums.Steps;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ProductConfig {
    private List<ProductData> products;

    public static class ProductData {
        private Long id;
        private String name;
        private String material;
        private Double price;
        private Map<Steps, Integer> stepsOfFabrication;

        // Getters
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getMaterial() {
            return material;
        }

        public Double getPrice() {
            return price;
        }

        public Map<Steps, Integer> getStepsOfFabrication() {
            return stepsOfFabrication;
        }
    }

    public ProductConfig(String filePath) throws IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);
        Type productListType = new TypeToken<List<ProductData>>() {}.getType();
        products = gson.fromJson(jsonObject.get("products"), productListType);
    }

    public List<ProductData> getProducts() {
        return products;
    }
}


