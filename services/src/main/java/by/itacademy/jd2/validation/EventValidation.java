package by.itacademy.jd2.validation;

import by.itacademy.jd2.event.Event;

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
    public String checkEventParam(Event event){
        String errorMessage = null;
        if(isEqualsTeamOneAndTwo(event.getTeamOneId(),event.getTeamTwoId())){
            errorMessage = "Team one and team two must be different";
            return errorMessage;
        }
        if(!isStartTimeEarlierEndTime(event.getStartTime(),event.getEndTime())){
            errorMessage = "Starting time must be earlier than ending time";
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
