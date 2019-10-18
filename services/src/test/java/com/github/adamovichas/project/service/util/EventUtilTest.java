package com.github.adamovichas.project.service.util;

import com.github.adamovichas.project.model.dto.EventView;
import com.github.adamovichas.project.model.event.Factor;
import com.github.adamovichas.project.model.event.FactorName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventUtilTest {

    private EventUtil util = EventUtil.EVENT_UTIL;

    @Test
    public void createFactors(){
        double win = 2;
        double lose = 3;
        double draw = 4;
        final List<Factor> factors = util.createFactors(win, lose, draw);
        assertEquals(factors.get(0).getValue(),win);
        assertEquals(factors.get(1).getValue(),lose);
        assertEquals(factors.get(2).getValue(),draw);
    }

    @Test
    public void getFactorsByName(){
        EventView event = new EventView(100L,"Test", Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<Factor> factors= new ArrayList<>();
        String factorName = FactorName.win.toString();
        double factorValue = 2.5;
        factors.add(new Factor(FactorName.win,factorValue));
        factors.add(new Factor(FactorName.draw,3));
        factors.add(new Factor(FactorName.lose,2.1));
        event.setFactors(factors);

        Factor factorByName = util.getFactorByName(event, factorName);
        assertEquals(factorByName.getName().toString(),factorName);
        assertEquals(factorByName.getValue(),factorValue);
    }
}
