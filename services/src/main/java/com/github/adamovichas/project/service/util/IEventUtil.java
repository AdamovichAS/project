package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.model.view.EventView;
import com.github.adamovichas.project.model.factor.FactorDTO;

import java.util.List;

public interface IEventUtil {

    List<FactorDTO> createFactors(double winFactor, double loseFactor, double drawFactor);
  //  List<String> getViewForLogInUser(List<EventView> events);
    FactorDTO getFactorByName(EventView eventView, String factorName);
}
