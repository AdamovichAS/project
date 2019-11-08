package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.IMoneyData;
import com.github.adamovichas.project.dao.impl.MoneyData;
import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.service.data.IDataMoneyService;

public class DataMoneyService implements IDataMoneyService {

    private static volatile IDataMoneyService instance;

    private IMoneyData data;

    private DataMoneyService() {
        data = MoneyData.getInstance();
    }

    public static IDataMoneyService getInstance() {
        IDataMoneyService localInstance = instance;
        if (localInstance == null) {
            synchronized (IDataMoneyService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataMoneyService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean verification(String login) {
        return data.verification(login);
    }

    @Override
    public MoneyDTO getMoneyByLogin(String login) {
        return data.getMoneyByLogin(login);
    }

    @Override
    public boolean deposit(String login, double depositValue) {
        MoneyDTO moneyDTO = data.getMoneyByLogin(login);
        moneyDTO.setValue(moneyDTO.getValue() + depositValue);
        return data.updateMoneyValue(moneyDTO);
    }

    @Override
    public boolean withdrawal(String login, double withdrawalValue) {
        MoneyDTO moneyDTO = data.getMoneyByLogin(login);
        moneyDTO.setValue(moneyDTO.getValue() - withdrawalValue);
        return data.updateMoneyValue(moneyDTO);
    }
}
