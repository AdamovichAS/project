package com.github.adamovichas.project.project.dao;

import com.github.adamovichas.project.model.dto.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.dto.Money;

import java.util.List;

public interface IBetData {
    Money getMoneyByLogin(String userLogin);
    Long addBet(Bet bet);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void CancelBetById(Long idBet);
}
