package com.github.adamovichas.project.service.validation;

import com.github.adamovichas.project.model.dto.EventDTO;

import java.sql.Timestamp;

public interface IEventValidation {
    String checkEventParam(EventDTO eventDTO);
    Timestamp formatDate(String date);
}
