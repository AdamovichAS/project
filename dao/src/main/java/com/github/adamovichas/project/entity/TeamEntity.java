package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "team")
public class TeamEntity {

    private String name;
    private Long idLeague;
    private LeagueEntity league;
//    private List<EventEntity> events;

    public TeamEntity(String name, Long idLeague) {
        this.idLeague = idLeague;
        this.name = name;
    }

    public TeamEntity() {
    }

    @Id
    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "league_id", insertable=false, updatable=false)
    public Long getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(Long idLeague) {
        this.idLeague = idLeague;
    }


    @ManyToOne
    @PrimaryKeyJoinColumn(name = "league_id")
    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

//    @ManyToMany(mappedBy = "teams")
//    public List<EventEntity> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<EventEntity> events) {
//        this.events = events;
//    }


    @Override
    public String toString() {
        return name;
    }
}
