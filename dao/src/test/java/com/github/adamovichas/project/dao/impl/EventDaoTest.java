package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.dao.impl.util.IUtil;
import com.github.adamovichas.project.dao.impl.util.Util;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.EventStatisticDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.EventView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IEventDao eventDao;
    @Autowired
    private IUtil util;

    /**
     * Event
     */

    @Test
    public void addEvent(){
        EventDTO eventTest = util.createEventTest();
        Long id = eventDao.addEvent(eventTest);
        assertNotNull(id);
    }

    @Test
    public void eventIsExistFalse(){
        EventDTO eventTest = util.createEventTest();
        boolean result = eventDao.eventIsExist(eventTest);
        assertFalse(result);

    }

    @Test
    public void eventIsExistTrue(){
        EventDTO eventTest = util.createEventTest();
        eventDao.addEvent(eventTest);
        boolean result = eventDao.eventIsExist(eventTest);
        assertTrue(result);
    }

    @Test
    public void getEventById(){
        EventDTO eventTest = util.createEventTest();
        Long id = eventDao.addEvent(eventTest);
        EventDTO savedEventById = eventDao.getEventById(id);
        assertNotNull(savedEventById);
        assertEquals(savedEventById.getName(),"Arsenal - Aston Vila");
    }

    @Test
    public void getEventViewById(){
        EventDTO eventTest = util.createEventTest();
        final List<FactorDTO> factors = util.createFactors();
        Long eventId = eventDao.addEvent(eventTest);
        for (FactorDTO factor : factors) {
            factor.setEventId(eventId);
        }
        eventDao.addFactors(factors);
        List<FactorDTO> eventFactors = eventDao.getEventFactors(eventId);
        EventView eventView = eventDao.getEventViewById(eventId);
        assertNotEquals(eventView.getId(),eventTest.getId());
        assertEquals(eventView.getLeagueId(),eventTest.getLeagueId());
        assertEquals(eventView.getStartTime(),eventTest.getStartTime());
        assertEquals(eventView.getEndTime(),eventTest.getEndTime());
        assertEquals(eventView.getTeamOne(),eventTest.getTeamOne());
        assertEquals(eventView.getTeamTwo(),eventTest.getTeamTwo());
        assertEquals(eventView.getResultFactorId(),eventTest.getResultFactorId());
        assertEquals(eventFactors.size(),eventView.getFactors().size());

    }

    /**
     * Factor
     */

    @Test
    public void addFactorsAndGetFactors(){
        EventDTO eventTest = util.createEventTest();
        final List<FactorDTO> factors = util.createFactors();
        Long eventId = eventDao.addEvent(eventTest);
        for (FactorDTO factor : factors) {
            factor.setEventId(eventId);
        }
        eventDao.addFactors(factors);
        List<FactorDTO> factorsSaved = eventDao.getEventFactors(eventId);
        assertEquals(factorsSaved.size(),factors.size());
        for (FactorDTO dto : factorsSaved) {
            assertNotNull(dto.getId());
        }
    }

    /**
     * Statistic
     */

    @Test
    public void addStatistic(){
        EventDTO eventTest = util.createEventTest();
        EventStatisticDTO testStatistic = util.createTestStatistic();
        Long id = eventDao.addEvent(eventTest);
//        testStatistic.setEventId(id);
        final boolean b = eventDao.addStatistics(testStatistic,id);
        assertTrue(b);
    }

    @Test
    public void getEventStatistic(){
        EventDTO eventTest = util.createEventTest();
        EventStatisticDTO testStatistic = util.createTestStatistic();
        Long id = eventDao.addEvent(eventTest);
        eventDao.addStatistics(testStatistic,id);
        EventStatisticDTO eventStatistic = eventDao.getEventStatistic(id);
        assertEquals(eventStatistic.getTeamOneGoals(),testStatistic.getTeamOneGoals());
        assertEquals(eventStatistic.getTeamTwoGoals(),testStatistic.getTeamTwoGoals());
        assertEquals(eventStatistic.getEventId(),id);
    }

//    @Test
//    void getAllNotFinishedEvents(){
//   //     Long countAllNotFinishedEvents = util.getCountAllNotFinishedEvents();
//        final List<EventDTO> events = eventDao.getAllNotFinishedEvents();
//  //      assertEquals(events.size(),countAllNotFinishedEvents);
//    }








}
