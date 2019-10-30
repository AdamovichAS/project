package com.github.adamovichas.project.entity;

import com.github.adamovichas.project.model.factor.FactorName;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "factor_event")
public class FactorEntity {
    private Long id;
    private FactorName name;
    private double value;
    private Long eventID;
    private EventEntity event;
    private List<BetEntity>bets;

    public FactorEntity(FactorName name, double value) {
        this.name = name;
        this.value = value;
    }

    public FactorEntity(Long id, FactorName name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public FactorEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name",nullable = false)
    @Enumerated(EnumType.STRING)
    public FactorName getName() {
        return name;
    }

    public void setName(FactorName name) {
        this.name = name;
    }

    @Column(name = "value", nullable = false)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name = "event_id",nullable = false, updatable = false, insertable = false)
    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    @OneToMany(mappedBy = "factor")
    public List<BetEntity> getBets() {
        return bets;
    }

    public void setBets(List<BetEntity> bets) {
        this.bets = bets;
    }
}
