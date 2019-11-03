package com.github.adamovichas.project.model.dto;


import com.github.adamovichas.project.model.factor.FactorDTO;

import java.sql.Timestamp;

import java.util.List;

public class EventDTO {
    private Long id;
    private String teamOne;
    private String teamTwo;
    private Long leagueId;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<FactorDTO>factors;
    private Long resultFactorId;

    public EventDTO(String teamOne, String teamTwo, Long leagueId, Timestamp startTime, Timestamp endTime) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.leagueId = leagueId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public EventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
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

    public void setFactors(List<FactorDTO> factors) {
        this.factors = factors;
    }

    public Long getResultFactorId() {
        return resultFactorId;
    }

    public void setResultFactorId(Long resultFactorId) {
        this.resultFactorId = resultFactorId;
    }

    public String getName(){
        return teamOne + " - " + teamTwo;
    }

    public String getFactorsView(){
        StringBuilder sb = new StringBuilder();
        for (FactorDTO factor : getFactors()) {
            sb.append(factor.toString()).append(" | ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", teamOne=" + teamOne +
                ", teamTwo=" + teamTwo +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", factors=" + factors +
                ", resultFactorId=" + resultFactorId +
                '}';
    }
}