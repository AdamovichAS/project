package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICashAccountService {
    boolean verification(String login);
    CashAccountDTO getAccountByLogin(String login);

    @Transactional
    AppCashAccountDTO getAppCashAccount();

    boolean makeDeposit(String login, double depositValue);
    boolean withdrawal(String login, double withdrawalValue);

    void eventCashAccountsCalculation(List<FactorDTO> eventFactors, FactorDTO winningFactor);
}
