package ua.nure.bolhov.SummaryTask4.db.entity;

import java.util.Objects;

public class Car extends Entity {

    private String model;
    private long idBrand;
    private double cost;
    private long idCategory;

    public long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }

    private long idStatus;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idQuality) {
        this.idCategory = idQuality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public int compareTo(Object o) {
        Car w = (Car) o;
        return model.compareTo(w.model);
    }
}
