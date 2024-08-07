package ec.edu.uce.FabricaMusical.models;

import ec.edu.uce.FabricaMusical.models.enums.Steps;

import java.util.LinkedHashMap;
import java.util.Map;

public class Product {
    private int id;
    private String name;
    private String material;
    private Double price;
    private Map<Steps, Integer> stepsOfFabrication;

    public Product() {
        this.stepsOfFabrication = new LinkedHashMap<>();
    }

    public Product(int id, String name, String material, Double price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.price = price;
        this.stepsOfFabrication = new LinkedHashMap<>();
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<Steps, Integer> getStepsOfFabrication() {
        return stepsOfFabrication;
    }

    public void setStepsOfFabrication(Map<Steps, Integer> stepsOfFabrication) {
        this.stepsOfFabrication = stepsOfFabrication;
    }

    public void addStepsOfFabrication(Steps steps, int time) {
        stepsOfFabrication.put(steps, time);
    }
}
