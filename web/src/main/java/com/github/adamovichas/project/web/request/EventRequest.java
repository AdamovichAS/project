package com.github.adamovichas.project.web.request;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRequest {

    private String teamOne;
    private String teamTwo;
    private Long leagueId;
    private String start;
    private String end;
    private double win;
    private double draw;
    private double lose;

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public double getDraw() {
        return draw;
    }

    public void setDraw(double draw) {
        this.draw = draw;
    }

    public double getLose() {
        return lose;
    }

    public void setLose(double lose) {
        this.lose = lose;
    }

    public EventDTO getEventDto(){
        EventDTO event = new EventDTO();
        event.setTeamOne(teamOne);
        event.setTeamTwo(teamTwo);
        event.setLeagueId(leagueId);
        event.setStartTime(formatDate(start));
        event.setEndTime(formatDate(end));
        return event;
    }

    public List<FactorDTO> getFactors(){
        List<FactorDTO> factors = new ArrayList<>();
        factors.add(new FactorDTO(FactorName.win,win));
        factors.add(new FactorDTO(FactorName.lose,lose));
        factors.add(new FactorDTO(FactorName.draw,draw));
        return factors;
    }

    public static Timestamp formatDate(String date) {
        DateFormat formatOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date dateOfString = null;
        try {
            dateOfString = formatInput.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatResult = formatOutput.format(dateOfString);
        return Timestamp.valueOf(formatResult);
    }
}
