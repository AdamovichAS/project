package com.github.adamovichas.project.service.validation;

import com.github.adamovichas.project.entity.Event;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(Event event);
    Timestamp formatDate(String date);
}
