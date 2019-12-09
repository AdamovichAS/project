package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.model.user.Role;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class Util {
    public UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setRole(Role.USER_VER);
        return  user;
    }


    public BetDTO createFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.FINISH);
        return betDTO;
    }

    public BetDTO createNotFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.RUN_TIME);
        return betDTO;
    }

    public BetDTO createCancelBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.CANCELD);
        return betDTO;
    }

    public EventDTO createEventTest(){
        EventDTO event = new EventDTO("Arsenal","Aston Vila", 1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        return event;
    }

    public List<FactorDTO> createFactors(){
        List<FactorDTO> factors= new ArrayList<>();
        factors.add(new FactorDTO(FactorName.win,2.5));
        factors.add(new FactorDTO(FactorName.draw,3));
        factors.add(new FactorDTO(FactorName.lose,2.1));
        return factors;
    }

    public UserPassportDTO createTestPassport() {
        UserDTO testUser = createTestUser();
        UserPassportDTO passportDTO = new UserPassportDTO();
        passportDTO.setUserLogin(testUser.getLogin());
        passportDTO.setPassSeries("MP");
        passportDTO.setLastName("Test");
        passportDTO.setFirstName("Test");
        return passportDTO;
    }


    public EventStatisticDTO createTestStatistic() {
        EventStatisticDTO statisticDto = new EventStatisticDTO();
        statisticDto.setTeamTwoGoals(2);
        statisticDto.setTeamOneGoals(2);
        return statisticDto;
    }
}
