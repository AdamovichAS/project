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
    public boolean createMoney(String login) {
        return data.createMoney(login);
    }

    @Override
    public MoneyDTO getMoneyByLogin(String login) {
        return data.getMoneyByLogin(login);
    }
}
