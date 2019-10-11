package com.github.adamovichas.DAO;

import com.github.adamovichas.DAO.impl.IDAOBet;
import com.github.adamovichas.dto.Bet;
import com.github.adamovichas.dto.BetView;
import com.github.adamovichas.dto.Money;
import com.github.adamovichas.mysql_data.BetData;
import com.github.adamovichas.mysql_data.impl.IBetData;

import java.util.List;

public enum DAOBet implements IDAOBet {
    DAO_BET;

    private IBetData data;

    DAOBet() {
        data = BetData.BET_DATA;
    }



    @Override
    public Money getMoneyByLogin(String userLogin) {
        return data.getMoneyByLogin(userLogin);
    }

    @Override
    public Long addBet(Bet bet) {
        return data.addBet(bet);
    }

    @Override
    public BetView getViewById(Long idBet) {
        return data.getViewById(idBet);
    }

    @Override
    public List<BetView> getNotFinishedBetByLogin(String login) {
        return data.getNotFinishedBetByLogin(login);
    }

    @Override
    public void cancelBetById(Long idBet) {
        data.CancelBetById(idBet);
    }
}
