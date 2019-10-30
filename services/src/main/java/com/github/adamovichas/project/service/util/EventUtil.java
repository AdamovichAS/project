package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EventUtil implements IEventUtil{
    EVENT_UTIL;


    @Override
    public List<FactorDTO> createFactors(double winFactor, double loseFactor, double drawFactor) {
        FactorDTO factorDTOWin = new FactorDTO(FactorName.win,winFactor);
        FactorDTO factorDTOLose = new FactorDTO(FactorName.lose,loseFactor);
        FactorDTO factorDTODraw = new FactorDTO(FactorName.draw,drawFactor);
        return new ArrayList<>(Arrays.asList(factorDTOWin, factorDTOLose, factorDTODraw));
    }

    @Override
    public FactorDTO getFactorByName(EventDTO eventDTO, String factorName) {
        FactorDTO factorDTOEv = null;
        for (FactorDTO factorDTO : eventDTO.getFactors()) {
            if (factorDTO.getName().toString().equals(factorName)) {
                factorDTOEv = new FactorDTO(factorDTO.getId(), factorDTO.getName(), factorDTO.getValue());
                break;
            }
        }
        return factorDTOEv;
    }


}
