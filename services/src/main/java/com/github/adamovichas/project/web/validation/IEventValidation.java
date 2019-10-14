package com.github.adamovichas.project.web.validation;

import com.github.adamovichas.project.web.event.Event;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(Event event);
    Timestamp formatDate(String date);
}
