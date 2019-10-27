package com.github.adamovichas.project;

import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.dto.MoneyDTO;

import java.util.List;

public interface IBetData {
    MoneyDTO getMoneyByLogin(String userLogin);
    Long addBet(BetDTO betDTO);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
    void CancelBetById(Long idBet);
}
