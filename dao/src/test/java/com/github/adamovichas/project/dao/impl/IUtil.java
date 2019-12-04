package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.model.dto.UserDTO;

public interface IUtil {
    UserDTO createTestUser();
//    void deleteTestUser(UserEntity user);
//    void deleteDeposit(String login);
    BetDTO createFinishedBet();
    BetDTO createNotFinishedBet();
    EventDTO createEventTest();
//    void deleteEvent(Long id);
//    Long getCountAllNotFinishedEvents();
}
