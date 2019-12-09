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

    List<BetDTO>getAllNotFinishedBetsByFactorId(Long factorId);

    void updateBetStatus(Long idBet, Status status);

    Long getCountBetsByLoginAbdStatus(String login, Status status);

    List<BetView> getBetsOnPageByLoginAndStatus(String login, Status status, int page, int pageSize);
}
