package com.github.adamovichas.project.model.dto;

public class EventStatisticDTO {


    private Long eventId;
    private int teamOneGoals;
    private int teamTwoGoals;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getTeamOneGoals() {
        return teamOneGoals;
    }

    public void setTeamOneGoals(int teamOneGoals) {
        this.teamOneGoals = teamOneGoals;
    }

    public int getTeamTwoGoals() {
        return teamTwoGoals;
    }

    public void setTeamTwoGoals(int teamTwoGoals) {
        this.teamTwoGoals = teamTwoGoals;
    }
}
