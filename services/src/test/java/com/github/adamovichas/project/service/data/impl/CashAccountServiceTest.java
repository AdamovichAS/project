package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.AppCashAccountDao;
import com.github.adamovichas.project.dao.impl.BetDao;
import com.github.adamovichas.project.dao.impl.UserDao;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class CashAccountServiceTest {

    @Mock
    public UserDao userDao;

    @Mock
    public BetDao betDao;

    @Mock
    public AppCashAccountDao cashAccountDao;

    @InjectMocks
    public CashAccountService cashAccountService;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAccountByLogin() {
        final CashAccountDTO testCashAccount = createTestCashAccount();
        when(userDao.getCashAccountByLogin(testCashAccount.getLogin())).thenReturn(testCashAccount);
        cashAccountService.getAccountByLogin(testCashAccount.getLogin());
        verify(userDao, times(1)).getCashAccountByLogin(testCashAccount.getLogin());
    }

    @Test
    void getAppCashAccount() {
        cashAccountService.getAppCashAccount();
        verify(cashAccountDao, times(1)).getAppCashAccount();
    }

    @Test
    void makeDeposit() {
        final CashAccountDTO testCashAccount = createTestCashAccount();
        when(userDao.getCashAccountByLogin(testCashAccount.getLogin())).thenReturn(testCashAccount);
        final boolean result = cashAccountService.makeDeposit(testCashAccount.getLogin(), 100);
        verify(userDao, times(1)).updateCashAccountValue(testCashAccount);
        assertTrue(result);
        when(userDao.updateCashAccountValue(testCashAccount)).thenThrow(new RuntimeException());
        final boolean resultFalse = cashAccountService.makeDeposit(testCashAccount.getLogin(), 100);
        assertFalse(resultFalse);
    }

    @Test
    void makeWithdrawal(){
        final CashAccountDTO testCashAccount = createTestCashAccount();
        when(userDao.getCashAccountByLogin(testCashAccount.getLogin())).thenReturn(testCashAccount);
        final boolean result = cashAccountService.withdrawal(testCashAccount.getLogin(), 100);
        verify(userDao, times(1)).updateCashAccountValue(testCashAccount);
        assertTrue(result);
        when(userDao.updateCashAccountValue(testCashAccount)).thenThrow(new RuntimeException());
        final boolean resultFalse = cashAccountService.withdrawal(testCashAccount.getLogin(), 100);
        assertFalse(resultFalse);
    }

    @Test
    public void eventCashAccountsCalculation(){
        CashAccountDTO winningCashAcc = createTestCashAccount();
        CashAccountDTO loosingCashAcc = createTestCashAccount();
        loosingCashAcc.setLogin("test2");
        FactorDTO winningFactor = new FactorDTO();
        winningFactor.setId(1L);
        FactorDTO loosingFactor = new FactorDTO();
        loosingFactor.setId(2L);
        BetDTO winningBet = new BetDTO();
        winningBet.setUserLogin(winningCashAcc.getLogin());
        winningBet.setMoney(100);
        winningBet.setFactorId(winningFactor.getId());
        winningBet.setStatus(Status.RUN_TIME);
        BetDTO loosingBet = new BetDTO();
        loosingBet.setUserLogin(loosingCashAcc.getLogin());
        loosingBet.setMoney(100);
        loosingBet.setFactorId(loosingBet.getFactorId());
        loosingBet.setStatus(Status.RUN_TIME);
        List<BetDTO>loosingBets = new ArrayList<>(Collections.singletonList(loosingBet));
        List<BetDTO>winningBets = new ArrayList<>(Collections.singletonList(winningBet));
        List<FactorDTO> factors = new ArrayList<>(Arrays.asList(winningFactor,loosingFactor));

        when(betDao.getAllNotFinishedBetsByFactorId(winningFactor.getId())).thenReturn(winningBets);
        when(betDao.getAllNotFinishedBetsByFactorId(loosingFactor.getId())).thenReturn(loosingBets);
        when(userDao.getCashAccountByLogin(winningBet.getUserLogin())).thenReturn(winningCashAcc);
        cashAccountService.eventCashAccountsCalculation(factors,winningFactor);
        verify(userDao,times(1)).updateCashAccountValue(winningCashAcc);
        verify(cashAccountDao,times(2)).updateBalance(anyDouble());
        verify(betDao,times(2)).updateBetStatus(any(),any(Status.class));
    }

    CashAccountDTO createTestCashAccount() {
        final CashAccountDTO cashAccountDTO = new CashAccountDTO();
        cashAccountDTO.setLogin("test");
        cashAccountDTO.setValue(100);
        return cashAccountDTO;
    }
}
