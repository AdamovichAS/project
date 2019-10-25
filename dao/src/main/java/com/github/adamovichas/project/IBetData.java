package com.github.adamovichas.project;

import com.github.adamovichas.project.entity.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.dto.MoneyDTO;

import java.util.List;

public interface IBetData {
    MoneyDTO getMoneyByLogin(String userLogin);
    Long addBet(Bet bet);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void CancelBetById(Long idBet);
}
