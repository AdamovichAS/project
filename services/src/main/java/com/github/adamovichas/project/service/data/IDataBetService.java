package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.entity.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.entity.Money;

import java.util.List;

public interface IDataBetService {
    Money getMoneyByLogin(String userLogin);
    Long addBet(Bet bet);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void cancelBetById(Long idBet);
}
