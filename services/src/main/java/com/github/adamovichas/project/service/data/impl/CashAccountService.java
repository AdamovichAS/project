package com.github.adamovichas.project.service.data.impl;


import com.github.adamovichas.project.dao.IAppCashAccountDao;
import com.github.adamovichas.project.dao.IBetDao;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.service.data.ICashAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class CashAccountService implements ICashAccountService {
    private static final Logger log = LoggerFactory.getLogger(ICashAccountService.class);

    private final IBetDao betDao;
    private final IUserDao userDao;
    private final IAppCashAccountDao appCashAccountDao;

    public CashAccountService(IBetDao betDao, IUserDao userDao, IAppCashAccountDao appCashAccountDao) {
        this.betDao = betDao;
        this.userDao = userDao;
        this.appCashAccountDao = appCashAccountDao;
    }



    @Override
    @Transactional
    public CashAccountDTO getAccountByLogin(String login) {
        return userDao.getCashAccountByLogin(login);
    }

    @Override
    @Transactional
    public AppCashAccountDTO getAppCashAccount(){
        return appCashAccountDao.getAppCashAccount();
    }

    @Override
    @Transactional
    public boolean makeDeposit(String login, double depositValue) {
        CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() + depositValue);
        try {
            userDao.updateCashAccountValue(cashAccountDTO);
            return true;
        } catch (Exception e){
            log.error("Deposit failed, login {} value {} at {}",login, depositValue, LocalDateTime.now(),e);
            return false;
        }

    }

    @Override
    @Transactional
    public boolean withdrawal(String login, double withdrawalValue) {
        CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() - withdrawalValue);
        try {
            userDao.updateCashAccountValue(cashAccountDTO);
            return true;
        } catch (Exception e){
            log.error("Withdrawal failed, login {} value {} at {}",login, withdrawalValue, LocalDateTime.now(),e);
            return false;
        }
    }

    @Override
    @Transactional
    public void eventCashAccountsCalculation(List<FactorDTO> eventFactors, FactorDTO winningFactor) {
        for (FactorDTO eventFactor : eventFactors) {
            if(eventFactor.getId().equals(winningFactor.getId())){
                List<BetDTO> winningBets = betDao.getAllNotFinishedBetsByFactorId(eventFactor.getId());
                if(!winningBets.isEmpty()) {
                    for (BetDTO winningBet : winningBets) {
                        CashAccountDTO userCashAcc = userDao.getCashAccountByLogin(winningBet.getUserLogin());
                        double win = winningBet.getMoney() * winningFactor.getValue();
                        userCashAcc.setValue(userCashAcc.getValue() + (win));
                        userDao.updateCashAccountValue(userCashAcc);
                        appCashAccountDao.updateBalance(-win);
                        betDao.updateBetStatus(winningBet.getId(), Status.FINISH);
                    }
                }
            }else {
                List<BetDTO> losingBets = betDao.getAllNotFinishedBetsByFactorId(eventFactor.getId());
                if(!losingBets.isEmpty()){
                    for (BetDTO losingBet : losingBets) {
                        appCashAccountDao.updateBalance(losingBet.getMoney());
                        betDao.updateBetStatus(losingBet.getId(),Status.FINISH);
                    }
                }
            }
        }
    }
}
