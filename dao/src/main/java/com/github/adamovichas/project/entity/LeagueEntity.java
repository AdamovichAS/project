package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "league")
public class LeagueEntity {
    private Long id;
    private String name;
    private List<TeamEntity>teams;

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

    @OneToMany(mappedBy = "league")
    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return name;
    }
}