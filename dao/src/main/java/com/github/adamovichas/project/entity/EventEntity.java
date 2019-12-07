package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "event")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventEntity {
    private Long id;
    private String teamOneId;
    private String teamTwoId;
    private Long leagueId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long resultFactorId;
    private List<FactorEntity> factors;
    private LeagueEntity league;
    private EventStatisticEntity statistic;
//    private List<TeamEntity> teams;

//    public EventEntity(Long teamOneId, Long teamTwoId, Timestamp startTime, Timestamp endTime) {
//        this.teamOneId = teamOneId;
//        this.teamTwoId = teamTwoId;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }


    public EventEntity() {
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

    @Column(name = "team_one", nullable = false)
    public String getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(String teamOneId) {
        this.teamOneId = teamOneId;
    }

    @Column(name = "team_two", nullable = false)
    public String getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(String teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    @Column(name = "league_id", nullable = false, insertable = false, updatable = false)
    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

    @Column(name = "start_time", nullable = false)
  //  @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time", nullable = false)
 //   @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Column(name = "result")
    public Long getResultFactorId() {
        return resultFactorId;
    }

    public void setResultFactorId(Long resultFactorId) {
        this.resultFactorId = resultFactorId;
    }

    @OneToMany(targetEntity = com.github.adamovichas.project.entity.FactorEntity.class,
            mappedBy = "event",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public List<FactorEntity> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorEntity> factors) {
        this.factors = factors;
    }

    @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public EventStatisticEntity getStatistic() {
        return statistic;
    }

    public void setStatistic(EventStatisticEntity statistic) {
        this.statistic = statistic;
    }

    @Transient
    public String getName(){
        return teamOneId + " - " + teamTwoId;
    }


//    @ManyToMany
//    @JoinTable(name = "event_team_relation", joinColumns = {@JoinColumn(name = "id_team")},
//            inverseJoinColumns = {@JoinColumn(name = "id_event")}
//    )
//    public List<TeamEntity> getTeams() {
//        return teams;
//    }
//
//    public void setTeams(List<TeamEntity> teams) {
//        this.teams = teams;
//    }

    @Override
    public String toString() {
        return "EventDTO{" +
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
