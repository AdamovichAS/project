package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.UserDTO;

public interface IUtil {
    UserDTO createTestUser();
//    void deleteTestUser(UserEntity user);
//    void deleteDeposit(String login);
    BetDTO createFinishedBet();
    BetDTO createNotFinishedBet();
    BetDTO createCanselBet();
    EventDTO createEventTest();
    AppCashAccountDTO createAppCashAccount();
//    void deleteEvent(Long id);
//    Long getCountAllNotFinishedEvents();
}
