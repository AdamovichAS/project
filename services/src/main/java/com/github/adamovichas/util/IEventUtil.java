package com.github.adamovichas.util;

import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.event.Factor;

import java.util.List;

public interface IEventUtil {

    List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor);
  //  List<String> getViewForLogInUser(List<EventView> events);
    Factor getFactorByName(EventView eventView, String factorName);
}
