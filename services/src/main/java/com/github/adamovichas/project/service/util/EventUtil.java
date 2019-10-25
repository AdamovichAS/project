package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.factor.Factor;
import com.github.adamovichas.project.model.factor.FactorName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EventUtil implements IEventUtil{
    EVENT_UTIL;


    @Override
    public List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor) {
        Factor factorWin = new Factor(FactorName.win,winFactor);
        Factor factorLose = new Factor(FactorName.lose,loseFactor);
        Factor factorDraw = new Factor(FactorName.draw,drawFactor);
        return new ArrayList<>(Arrays.asList(factorWin,factorLose,factorDraw));
    }

    @Override
    public Factor getFactorByName(EventView eventView, String factorName) {
        Factor factorEv = null;
        for (Factor factor : eventView.getFactors()) {
            if (factor.getName().toString().equals(factorName)) {
                factorEv = new Factor(factor.getId(),factor.getName(),factor.getValue());
                break;
            }
        }
        return factorEv;
    }


}
