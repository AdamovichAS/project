package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;

import java.util.List;

public interface ICashAccountService {
    CashAccountDTO verification(String login);
    CashAccountDTO getAccountByLogin(String login);
    CashAccountDTO makeDeposit(String login, double depositValue);
    CashAccountDTO withdrawal(String login, double withdrawalValue);

    void eventCashAccountsCalculation(List<FactorDTO> eventFactors, FactorDTO winningFactor);
}
