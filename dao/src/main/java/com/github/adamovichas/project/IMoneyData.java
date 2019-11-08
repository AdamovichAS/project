package com.github.adamovichas.project;

import com.github.adamovichas.project.model.dto.MoneyDTO;

public interface IMoneyData {
    boolean verification(String login);
    MoneyDTO getMoneyByLogin(String login);
    boolean updateMoneyValue(MoneyDTO money);
}
