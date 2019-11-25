package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.dto.CashAccountDTO;

public interface ICashAccountData {
    boolean verification(String login);
    CashAccountDTO getMoneyByLogin(String login);
    boolean updateMoneyValue(CashAccountDTO money);
}
