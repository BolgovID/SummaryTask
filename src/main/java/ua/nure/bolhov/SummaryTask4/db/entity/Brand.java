package ua.nure.bolhov.SummaryTask4.db.entity;

import java.util.Objects;

public class Brand extends Entity {
    private String brand;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand1 = (Brand) o;
        return brand.equals(brand1.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }

    @Override
    public int compareTo(Object o) {
        Brand w = (Brand) o;
        return brand.compareTo(w.brand);
    }
}
