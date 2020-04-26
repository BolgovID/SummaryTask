package ua.nure.bolhov.SummaryTask4.web.bean;

import java.util.Objects;

public class OperationCarBean {

    private long id;
    private String model;
    private double cost;
    private long idBrand;
    private long idCategory;

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (Objects.isNull(id) || id.isEmpty()) ? 0 : Long.parseLong(id);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = (Objects.isNull(cost) || cost.isEmpty()) ? 0 : Double.parseDouble(cost);
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(String idBrand) {
        this.idBrand = (Objects.isNull(idBrand) || idBrand.isEmpty()) ? 0 : Long.parseLong(idBrand);
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = (Objects.isNull(idCategory) || idCategory.isEmpty()) ? 0 : Long.parseLong(idCategory);
    }
}
