package com.github.adamovichas.project.project.dao;

import com.github.adamovichas.project.web.dto.Bet;
import com.github.adamovichas.project.web.dto.BetView;
import com.github.adamovichas.project.web.dto.Money;

import java.util.List;

public interface IBetData {
    Money getMoneyByLogin(String userLogin);
    Long addBet(Bet bet);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void CancelBetById(Long idBet);
}
