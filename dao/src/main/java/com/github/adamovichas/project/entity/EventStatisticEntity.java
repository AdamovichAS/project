package com.github.adamovichas.project.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@Entity
@Table(name = "event_statistic")
public class EventStatisticEntity {

    private Long eventId;
    private int teamOneGoals;
    private int teamTwoGoals;
    private EventEntity event;

    @Id
    @Column(name = "event_id", updatable = false)
    @GenericGenerator(name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "event"))
    @GeneratedValue(generator = "gen")
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Column(name = "team_one_goals")
    public int getTeamOneGoals() {
        return teamOneGoals;
    }

    public void setTeamOneGoals(int teamOneGoals) {
        this.teamOneGoals = teamOneGoals;
    }
    @Column(name = "team_two_goals")
    public int getTeamTwoGoals() {
        return teamTwoGoals;
    }

    public void setTeamTwoGoals(int teamTwoGoals) {
        this.teamTwoGoals = teamTwoGoals;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "event_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    public EventEntity getEvent() {
        return event;
    }
    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
