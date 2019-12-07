package com.github.adamovichas.project.model.factor;

public class FactorDTO {
    private Long id;
    private FactorName name;
    private double value;
    private Long eventId;

    public FactorDTO(FactorName name, double value) {
        this.name = name;
        this.value = value;
    }

    public FactorDTO(Long id, FactorName name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public FactorDTO() {
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f;",name,value);
    }
}
