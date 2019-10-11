package com.github.adamovichas.DAO.impl;

import com.github.adamovichas.dto.Bet;
import com.github.adamovichas.dto.BetView;
import com.github.adamovichas.dto.Money;

import java.util.List;

public interface IDAOBet {
    Money getMoneyByLogin(String userLogin);
    Long addBet(Bet bet);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void cancelBetById(Long idBet);
}
