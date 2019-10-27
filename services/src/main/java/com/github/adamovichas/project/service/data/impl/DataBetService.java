package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.IBetData;
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
    public MoneyDTO getMoneyByLogin(String userLogin) {
        return data.getMoneyByLogin(userLogin);
    }

    @Override
    public Long addBet(BetDTO betDTO) {
        return data.addBet(betDTO);
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
