package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.dto.CashAccountDTO;

public interface IUserCashAccountDao {
    boolean addUserCashAccount(String login);
    CashAccountDTO getCashAccountByLogin(String login);
    boolean updateCashAccountValue(CashAccountDTO money);
}
