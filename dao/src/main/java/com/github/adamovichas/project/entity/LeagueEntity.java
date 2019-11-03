package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "league")
public class LeagueEntity {
    private Long id;
    private String name;
    private List<TeamEntity>teams;
    private List<EventEntity>events;

    public LeagueEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public LeagueEntity() {
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

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "league_team_dependency", joinColumns = {@JoinColumn(name = "league_id")},
            inverseJoinColumns = {@JoinColumn(name = "team")}
    )
    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }

    @OneToMany(mappedBy = "league")
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