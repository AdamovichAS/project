package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.MoneyDTO;

public interface IDataMoneyService {
    boolean createMoney(String login);
    MoneyDTO getMoneyByLogin(String login);
}
