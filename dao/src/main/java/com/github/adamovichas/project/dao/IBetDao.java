package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;

import java.util.List;

public interface IBetDao {

    Long addBet(BetDTO betDTO);
    BetView getViewById(Long idBet);
 //   List<BetView> getAllByUserAndStatus(String login);

    List<BetView> getAllByUserAndStatus(String login, Status status);

    void CancelBetById(Long idBet);

//    Long getCountBetsByLogin(String login);

    List<BetView> getBetsOnPageByLogin(String login, int page, int pageSize);
}
