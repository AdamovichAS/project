package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.model.dto.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.dto.Money;
import com.github.adamovichas.project.project.dao.IBetData;
import com.github.adamovichas.project.dao.impl.BetData;

import java.util.List;

public class DataBetService implements IDataBetService {

    private static volatile IDataBetService instance;

    private IBetData data;

    private DataBetService() {
        data = BetData.BET_DATA;
    }

    public static IDataBetService getInstance() {
        IDataBetService localInstance = instance;
        if (localInstance == null) {
            synchronized (IDataBetService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataBetService();
                }
            }
        }
        return localInstance;
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
