package com.github.adamovichas.project.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeamEntity {

    private String name;
//    private Long idLeague;
    private List<LeagueEntity> leagues;


    public TeamEntity(String name) {
 //       this.idLeague = idLeague;
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


//    @Column(name = "league_id", insertable=false, updatable=false)
//    public Long getIdLeague() {
//        return idLeague;
//    }
//
//    public void setIdLeague(Long idLeague) {
//        this.idLeague = idLeague;
//    }


    @ManyToMany(mappedBy = "teams")
    public List<LeagueEntity> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<LeagueEntity> league) {
        this.leagues = league;
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
