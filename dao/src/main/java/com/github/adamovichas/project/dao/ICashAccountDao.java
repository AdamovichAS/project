package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.dto.CashAccountDTO;

public interface ICashAccountDao {
    boolean create(String login);
    CashAccountDTO getCashAccountByLogin(String login);
    boolean updateCashAccountValue(CashAccountDTO money);
}
