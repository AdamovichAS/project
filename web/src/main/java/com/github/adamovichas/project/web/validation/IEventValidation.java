package com.github.adamovichas.project.web.validation;

import com.github.adamovichas.project.web.request.EventRequest;

public interface IEventValidation {
    String checkEventParam(EventRequest eventRequest);
}
