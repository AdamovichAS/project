package com.github.adamovichas.project.dao.impl.util;

import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;

import java.util.List;

public interface IUtil {
    UserDTO createTestUser();
//    void deleteTestUser(UserEntity user);
//    void deleteDeposit(String login);
    BetDTO createFinishedBet();
    BetDTO createNotFinishedBet();
    BetDTO createCancelBet();
    EventDTO createEventTest();
    List<FactorDTO> createFactors();
    UserPassportDTO createTestPassport();
    EventStatisticDTO createTestStatistic();
//    AppCashAccountDTO createAppCashAccount();
//    void deleteEvent(Long id);
//    Long getCountAllNotFinishedEvents();
}
