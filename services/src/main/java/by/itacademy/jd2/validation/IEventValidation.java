package by.itacademy.jd2.validation;

import by.itacademy.jd2.event.Event;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(Event event);
    Timestamp formatDate(String date);
}
