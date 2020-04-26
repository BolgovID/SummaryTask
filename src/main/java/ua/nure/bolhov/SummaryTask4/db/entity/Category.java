package ua.nure.bolhov.SummaryTask4.db.entity;

import java.util.Objects;

public class Category extends Entity{

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return category.equals(category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    @Override
    public int compareTo(Object o) {
        Category w = (Category) o;
        return category.compareTo(w.category);
    }
}
