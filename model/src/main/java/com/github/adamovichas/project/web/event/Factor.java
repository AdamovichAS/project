package com.github.adamovichas.project.web.event;

public class Factor {
    private Long id;
    private FactorName name;
    private double value;
    private Long eventID;

    public Factor(FactorName name, double value) {
        this.name = name;
        this.value = value;
    }

    public Factor(Long id, FactorName name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Factor() {
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

    @Override
    public String toString() {
        return String.format("%s: %.2f;",name,value);
    }
}
