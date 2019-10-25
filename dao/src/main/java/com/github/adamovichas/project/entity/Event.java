package com.github.adamovichas.project.entity;


import com.github.adamovichas.project.model.factor.Factor;

import javax.persistence.*;
import java.sql.Timestamp;

import java.util.List;

public class Event {
    private Long id;
    private Long teamOneId;
    private Long teamTwoId;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<Factor>factors;
    private Long resultFactorId;

    public Event(Long teamOneId, Long teamTwoId, Timestamp startTime, Timestamp endTime) {
        this.teamOneId = teamOneId;
        this.teamTwoId = teamTwoId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(Long teamOneId) {
        this.teamOneId = teamOneId;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(Long teamTwoId) {
        this.teamTwoId = teamTwoId;
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

    public Long getResultFactorId() {
        return resultFactorId;
    }

    public void setResultFactorId(Long resultFactorId) {
        this.resultFactorId = resultFactorId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", teamOneId=" + teamOneId +
                ", teamTwoId=" + teamTwoId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", factors=" + factors +
                ", resultFactorId=" + resultFactorId +
                '}';
    }
}
