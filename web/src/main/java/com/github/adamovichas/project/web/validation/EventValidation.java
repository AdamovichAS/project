package com.github.adamovichas.project.web.validation;

import com.github.adamovichas.project.web.request.EventRequest;

import java.sql.Timestamp;

public class EventValidation implements IEventValidation{

    @Override
    public String checkEventParam(EventRequest eventRequest) {
        String errorMessage = null;
        if(isEqualsTeamOneAndTwo(eventRequest.getTeamOne(), eventRequest.getTeamTwo())){
            errorMessage = "add_event.error_teams";
            return errorMessage;
        }
        if(!isStartTimeEarlierEndTime(EventRequest.formatDate(eventRequest.getStart()), EventRequest.formatDate(eventRequest.getEnd()))){
            errorMessage = "add_event.error_time";
        }
        if(eventRequest.getWin() <= 1 || eventRequest.getDraw() <= 1 || eventRequest.getLose() <= 1){
            errorMessage = "add_event.error_factors";
        }
        return errorMessage;
    }

    private boolean isStartTimeEarlierEndTime(Timestamp start, Timestamp end) {
        return start.compareTo(end) < 0;
    }

    private boolean isEqualsTeamOneAndTwo(String oneId, String twoId) {
        return oneId.equals(twoId);
    }
}
