package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.IUserCashAccountDao;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IDataCashAccountService;

public class DataCashAccountService implements IDataCashAccountService {

    private final IUserCashAccountDao data;

    public DataCashAccountService(IUserCashAccountDao cashAccountData) {
        data = cashAccountData;
    }


    @Override
    public boolean verification(String login) {
        return data.addUserCashAccount(login);
    }

    @Override
    public CashAccountDTO getAccountByLogin(String login) {
        return data.getCashAccountByLogin(login);
    }

    @Override
    public boolean makeDeposit(String login, double depositValue) {
        CashAccountDTO cashAccountDTO = data.getCashAccountByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() + depositValue);
        return data.updateCashAccountValue(cashAccountDTO);
    }

    @Override
    public boolean withdrawal(String login, double withdrawalValue) {
        CashAccountDTO cashAccountDTO = data.getCashAccountByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() - withdrawalValue);
        return data.updateCashAccountValue(cashAccountDTO);
    }
}
