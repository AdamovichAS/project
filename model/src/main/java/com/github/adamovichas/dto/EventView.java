package com.github.adamovichas.dto;

import com.github.adamovichas.event.Factor;

import java.sql.Timestamp;
import java.util.List;

public class EventView {

    private Long id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<Factor> factors;
    private double resultFactorValue;

    public EventView() {
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

    public List<Factor> getFactors() {
        return factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public double getResultFactorValue() {
        return resultFactorValue;
    }

    public void setResultFactorValue(double resultFactorValue) {
        this.resultFactorValue = resultFactorValue;
    }


}
