package com.github.adamovichas.project.web.request;

public class BetRequest {

    private Long eventId;
    private double moneyForBet;
    private String factorName;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public double getMoneyForBet() {
        return moneyForBet;
    }

    public void setMoneyForBet(double moneyForBet) {
        this.moneyForBet = moneyForBet;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }
}
