package com.github.adamovichas.project.service.validation;

import com.github.adamovichas.project.model.event.Event;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(Event event);
    Timestamp formatDate(String date);
}
