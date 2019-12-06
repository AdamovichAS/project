package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDto;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional
//@Rollback(value = false)
public class EventDaoTest {
    @Autowired
    private IEventDao dataEvent;
    @Autowired
    private IUtil util;

    @Test
    public void addEvent(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        assertNotNull(id);
    }

    @Test
    public void eventIsExistFalse(){
        EventDTO eventTest = util.createEventTest();
        boolean result = dataEvent.eventIsExist(eventTest);
        assertFalse(result);

    }

    @Test
    public void eventIsExistTrue(){
        EventDTO eventTest = util.createEventTest();
        dataEvent.addEvent(eventTest);
        boolean result = dataEvent.eventIsExist(eventTest);
        assertTrue(result);
    }

    @Test
    public void getEventById(){
        EventDTO eventTest = util.createEventTest();
        Long id = dataEvent.addEvent(eventTest);
        EventDTO savedEventById = dataEvent.getEventById(id);
        assertNotNull(savedEventById);
        assertEquals(savedEventById.getName(),"Arsenal - Aston Vila");
    }

    @Test
    public void addStatistic(){
        EventDTO eventTest = util.createEventTest();
        EventStatisticDto testStatistic = util.createTestStatistic();
        Long id = dataEvent.addEvent(eventTest);
        final boolean b = dataEvent.addStatistics(testStatistic, id);
        assertTrue(b);
    }

//    @Test
//    void getAllNotFinishedEvents(){
//   //     Long countAllNotFinishedEvents = util.getCountAllNotFinishedEvents();
//        final List<EventDTO> events = dataEvent.getAllNotFinishedEvents();
//  //      assertEquals(events.size(),countAllNotFinishedEvents);
//    }








}
