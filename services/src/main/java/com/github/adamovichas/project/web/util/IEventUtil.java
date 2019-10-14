package com.github.adamovichas.project.web.util;

import com.github.adamovichas.project.web.dto.EventView;
import com.github.adamovichas.project.web.event.Factor;

import java.util.List;

public interface IEventUtil {

    List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor);
  //  List<String> getViewForLogInUser(List<EventView> events);
    Factor getFactorByName(EventView eventView, String factorName);
}
