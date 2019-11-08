package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;

import java.util.List;

public interface IDataBetService {
    Long addBet(BetDTO betDTO);
    BetView getViewById(Long idBet);
    List<BetView> getNotFinishedBetByLogin(String login);
//    void cancelBetById(Long idBet);

    void cancelBetById(List<Long> idBets);

    Long getBetMaxPagesByLogin(String login);

    List<BetView> getBetsByLoginOnCorrentPage(String login, int page);
}
