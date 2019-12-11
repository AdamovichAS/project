package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.service.data.impl.Util;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventUtilTest {

    private IEventUtil util;

    @Test
    public void createFactors(){
        double win = 2;
        double lose = 3;
        double draw = 4;
        final List<FactorDTO> factorDTOS = util.createFactors(win, lose, draw);
        assertEquals(factorDTOS.get(0).getValue(),win);
        assertEquals(factorDTOS.get(1).getValue(),lose);
        assertEquals(factorDTOS.get(2).getValue(),draw);
    }

    @Test
    public void getFactorsByName(){
        EventDTO event = new EventDTO("Test2","Test",1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<FactorDTO> factorDTOS = new ArrayList<>();
        String factorName = FactorName.win.toString();
        double factorValue = 2.5;
        factorDTOS.add(new FactorDTO(FactorName.win,factorValue));
        factorDTOS.add(new FactorDTO(FactorName.draw,3));
        factorDTOS.add(new FactorDTO(FactorName.lose,2.1));
//        event.setFactors(factorDTOS);
//
//        FactorDTO factorDTOByName = util.getFactorByName(event, factorName);
//        assertEquals(factorDTOByName.getName().toString(),factorName);
//        assertEquals(factorDTOByName.getValue(),factorValue);
    }
}
