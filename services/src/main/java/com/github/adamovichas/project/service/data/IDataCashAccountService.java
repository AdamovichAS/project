package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.CashAccountDTO;

public interface IDataCashAccountService {
    boolean verification(String login);
    CashAccountDTO getAccountByLogin(String login);
    boolean makeDeposit(String login, double depositValue);
    boolean withdrawal(String login, double withdrawalValue);
}
