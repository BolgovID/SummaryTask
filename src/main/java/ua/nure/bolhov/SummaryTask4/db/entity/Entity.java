package ua.nure.bolhov.SummaryTask4.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Comparable{


    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

