package com.github.adamovichas.project.model.view;

import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;

import java.sql.Timestamp;
import java.util.List;

public class EventView {

    private Long id;
    private String teamOne;
    private String teamTwo;
    private Long leagueId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long resultFactorId;
    private List<FactorDTO> factors;
    private EventStatisticDTO statistic;

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

    public EventStatisticDTO getStatistic() {
        return statistic;
    }

    public void setStatistic(EventStatisticDTO statistic) {
        this.statistic = statistic;
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
        return "EventView{" +
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
