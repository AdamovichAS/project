package com.github.adamovichas.validation;

import com.github.adamovichas.event.Event;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(Event event);
    Timestamp formatDate(String date);
}
