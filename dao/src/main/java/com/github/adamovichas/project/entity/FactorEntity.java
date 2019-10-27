package com.github.adamovichas.project.entity;

import com.github.adamovichas.project.model.factor.FactorName;

public class FactorEntity {
    private Long id;
    private FactorName name;
    private double value;
    private Long eventID;

    public FactorEntity(FactorName name, double value) {
        this.name = name;
        this.value = value;
    }

    public FactorEntity(Long id, FactorName name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public FactorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FactorName getName() {
        return name;
    }

    public void setName(FactorName name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }
}
