package ec.edu.uce.FabricaMusical.models;

import ec.edu.uce.FabricaMusical.models.enums.Steps;

import java.util.LinkedHashMap;
import java.util.Map;

public class Product {
    private Long id;
    private String name;
    private String material;
    private Double price;
    private Map<Steps, Integer> stepsOfFabrication; // Integer representa el tiempo de cada paso

    public Product(Long id, String name, String material, Double price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.price = price;
        this.stepsOfFabrication = new LinkedHashMap<>();
    }

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

    public void addStepsOfFabrication(Steps steps, int time) {
        stepsOfFabrication.put(steps, time);
    }
}