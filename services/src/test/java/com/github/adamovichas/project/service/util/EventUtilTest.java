package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.service.config.ServiceConfig;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, ServiceConfig.class})
public class EventUtilTest {

    @Autowired
    private IEventUtil util;


    @Test
    public void getWinningFactor(){
        List<FactorDTO> factorDTOS = new ArrayList<>();
        factorDTOS.add(new FactorDTO(FactorName.win,2.5));
        factorDTOS.add(new FactorDTO(FactorName.draw,3));
        factorDTOS.add(new FactorDTO(FactorName.lose,2.1));
        EventStatisticDTO statisticDTO = new EventStatisticDTO();
        statisticDTO.setTeamOneGoals(1);
        statisticDTO.setTeamTwoGoals(1);
        FactorDTO draw = util.getWinningFactor(statisticDTO, factorDTOS);
        assertEquals(draw.getName(),FactorName.draw);
        statisticDTO.setTeamOneGoals(1);
        statisticDTO.setTeamTwoGoals(0);
        FactorDTO win = util.getWinningFactor(statisticDTO, factorDTOS);
        assertEquals(win.getName(),FactorName.win);
        statisticDTO.setTeamOneGoals(0);
        statisticDTO.setTeamTwoGoals(1);
        FactorDTO lose = util.getWinningFactor(statisticDTO, factorDTOS);
        assertEquals(lose.getName(),FactorName.lose);

    }

    @Test
    public void getFactorsByName(){
        List<FactorDTO> factorDTOS = new ArrayList<>();
        String factorName = FactorName.win.toString();
        double factorValue = 2.5;
        factorDTOS.add(new FactorDTO(FactorName.win,factorValue));
        factorDTOS.add(new FactorDTO(FactorName.draw,3));
        factorDTOS.add(new FactorDTO(FactorName.lose,2.1));

        FactorDTO factorDTOByName = util.getFactorByName(factorDTOS, factorName);
        assertEquals(factorDTOByName.getName().toString(),factorName);
        assertEquals(factorDTOByName.getValue(),factorValue);
    }
}
