package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.*;

public interface IUtil {
    UserDTO createTestUser();
//    void deleteTestUser(UserEntity user);
//    void deleteDeposit(String login);
    BetDTO createFinishedBet();
    BetDTO createNotFinishedBet();
    BetDTO createCanselBet();
    EventDTO createEventTest();
    UserPassportDTO createTestPassport();
//    AppCashAccountDTO createAppCashAccount();
//    void deleteEvent(Long id);
//    Long getCountAllNotFinishedEvents();
}
