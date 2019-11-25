package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.ICashAccountData;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IDataCashAccountService;

public class DataCashAccountService implements IDataCashAccountService {

    private final ICashAccountData data;

    public DataCashAccountService(ICashAccountData cashAccountData) {
        data = cashAccountData;
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
