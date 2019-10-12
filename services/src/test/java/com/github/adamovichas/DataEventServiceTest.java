package com.github.adamovichas;

import com.github.adamovichas.data.impl.DataEventService;
import com.github.adamovichas.dto.Bet;
import com.github.adamovichas.event.Event;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.event.FactorName;
import com.github.adamovichas.mysql_data.impl.DataEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class DataEventServiceTest {

    @Mock
    public DataEvent dataEvent;

    @Mock
    public DataEventService dataEventService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    /*public Bet createTestBet(){
        Bet bet = new Bet("test",100L,1000);
        bet.setId(50L);
        return bet;
    }*/
    Event createEventTest(){
        Event event = new Event(1L,2L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<Factor> factors= new ArrayList<>();
        event.setId(50L);
        factors.add(new Factor(FactorName.win,2.5));
        factors.add(new Factor(FactorName.draw,3));
        factors.add(new Factor(FactorName.lose,2.1));
        event.setFactors(factors);
        return event;
    }

    @Test
    public void eventIsExist(){
        Event eventTest = createEventTest();

    }
}
