package com.github.adamovichas.project.model.view;



import com.github.adamovichas.project.model.factor.FactorDTO;

import java.sql.Timestamp;
import java.util.List;

public class EventView {

    private Long id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<FactorDTO> factors;
    private double resultFactorValue;

    public EventView() {
    }

    public EventView(Long id, String name, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public List<FactorDTO> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorDTO> factorDTOS) {
        this.factors = factorDTOS;
    }

    public double getResultFactorValue() {
        return resultFactorValue;
    }

    public void setResultFactorValue(double resultFactorValue) {
        this.resultFactorValue = resultFactorValue;
    }


}
