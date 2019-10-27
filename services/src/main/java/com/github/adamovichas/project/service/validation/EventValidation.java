package com.github.adamovichas.project.service.validation;

import com.github.adamovichas.project.model.dto.EventDTO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum EventValidation implements IEventValidation {
    EVENT_VALIDATION;

    public boolean isStartTimeEarlierEndTime(Timestamp start, Timestamp end) {
        if(start.compareTo(end) >= 0){
            return false;
        }
        return true;
    }


    public boolean isEqualsTeamOneAndTwo(Long oneId, Long twoId) {
        if(oneId.equals(twoId)) {
            return true;
        }
        return false;
    }

    @Override
    public String checkEventParam(EventDTO eventDTO){
        String errorMessage = null;
        if(isEqualsTeamOneAndTwo(eventDTO.getTeamOneId(), eventDTO.getTeamTwoId())){
            errorMessage = "add_event.error_teams";
            return errorMessage;
        }
        if(!isStartTimeEarlierEndTime(eventDTO.getStartTime(), eventDTO.getEndTime())){
            errorMessage = "add_event.error_time";
        }
        return errorMessage;
    }

    @Override
    public Timestamp formatDate(String date) {
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
