package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
public class TeamEntity {

    private Long id;
    private Long idLeague;
    private String name;
    private LeagueEntity league;
    private List<EventEntity> events;

    public TeamEntity(Long id, Long idLeague, String name) {
        this.id = id;
        this.idLeague = idLeague;
        this.name = name;
    }

    public TeamEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "league_id", insertable=false, updatable=false)
    public Long getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(Long idLeague) {
        this.idLeague = idLeague;
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne
    @PrimaryKeyJoinColumn(name = "league_id")
    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

    @ManyToMany(mappedBy = "teams")
    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return name;
    }
}
