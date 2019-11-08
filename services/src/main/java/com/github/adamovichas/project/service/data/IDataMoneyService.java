package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.MoneyDTO;

public interface IDataMoneyService {
    boolean verification(String login);
    MoneyDTO getMoneyByLogin(String login);
    boolean deposit(String login, double depositValue);
    boolean withdrawal(String login, double withdrawalValue);
}
