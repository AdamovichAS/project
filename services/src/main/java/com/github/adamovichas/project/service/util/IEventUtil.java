package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.factor.Factor;

import java.util.List;

public interface IEventUtil {

    List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor);
  //  List<String> getViewForLogInUser(List<EventView> events);
    Factor getFactorByName(EventView eventView, String factorName);
}
