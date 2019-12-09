package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.view.BetView;

import java.util.List;

public interface IBetService {
    Long addBet(BetDTO betDTO);
    BetView getViewById(Long idBet);
    List<BetView> getAllByUserAndStatus(String login, Status status);
//    void cancelBetById(Long idBet);

    void cancelBetById(List<Long> idBets);
//    void eventCashAccountsCalculation(List<FactorDTO>eventFactors,FactorDTO winningFactor);
    Long getBetMaxPagesByLoginAndStatus(String login, Status status);

    List<BetView> getBetsByLoginOnCurrentPage(String login, Status status, int page);
}
