package com.github.adamovichas.project.service.util.event;

import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EventUtil implements IEventUtil{


    @Override
    public FactorDTO getFactorByName(List<FactorDTO> factors, String factorName) {
        FactorDTO factorDTOE = null;
        for (FactorDTO factorDTO : factors) {
            if (factorDTO.getName().toString().equals(factorName)) {
                factorDTOE = new FactorDTO(factorDTO.getId(), factorDTO.getName(), factorDTO.getValue());
                break;
            }
        }
        return factorDTOE;
    }


    @Override
    public FactorDTO getWinningFactor(EventStatisticDTO statisticDTO, List<FactorDTO> eventFactors){
        int goalsDifference = statisticDTO.getTeamOneGoals() - statisticDTO.getTeamTwoGoals();
        if(goalsDifference == 0){
            return getFactorByName(eventFactors, FactorName.draw.toString());
        }else if(goalsDifference > 0){
            return getFactorByName(eventFactors,FactorName.win.toString());
        }
        return getFactorByName(eventFactors,FactorName.lose.toString());
    }
}
