package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.ICashAccountData;
import com.github.adamovichas.project.dao.impl.CashAccountData;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IDataCashAccountService;

public class DataCashAccountService implements IDataCashAccountService {

    private static volatile IDataCashAccountService instance;

    private ICashAccountData data;

    private DataCashAccountService() {
        data = CashAccountData.getInstance();
    }

    public static IDataCashAccountService getInstance() {
        IDataCashAccountService localInstance = instance;
        if (localInstance == null) {
            synchronized (IDataCashAccountService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataCashAccountService();
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
    public CashAccountDTO getAccountByLogin(String login) {
        return data.getMoneyByLogin(login);
    }

    @Override
    public boolean makeDeposit(String login, double depositValue) {
        CashAccountDTO cashAccountDTO = data.getMoneyByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() + depositValue);
        return data.updateMoneyValue(cashAccountDTO);
    }

    @Override
    public boolean withdrawal(String login, double withdrawalValue) {
        CashAccountDTO cashAccountDTO = data.getMoneyByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() - withdrawalValue);
        return data.updateMoneyValue(cashAccountDTO);
    }
}
