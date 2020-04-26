package ua.nure.bolhov.SummaryTask4.db.dto;

import ua.nure.bolhov.SummaryTask4.db.entity.Entity;

import java.util.Objects;

public class CarDTO extends Entity {

    private String model;
    private String brand;
    private double cost;
    private String category;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CarDTO [id=" + getId() + ", model=" + getModel() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return model.equals(carDTO.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public int compareTo(Object o) {
        CarDTO w = (CarDTO) o;
        return model.compareTo(w.model);
    }
}
