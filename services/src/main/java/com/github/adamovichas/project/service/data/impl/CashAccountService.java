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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CashAccountService implements ICashAccountService {

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
    public boolean verification(String login) {
        userDao.addUserCashAccount(login);
        return true;
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
        userDao.updateCashAccountValue(cashAccountDTO);
        return true;
    }

    @Override
    @Transactional
    public boolean withdrawal(String login, double withdrawalValue) {
        CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(login);
        cashAccountDTO.setValue(cashAccountDTO.getValue() - withdrawalValue);
        userDao.updateCashAccountValue(cashAccountDTO);
        return true;
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
//                        CashAccountDTO userCashAcc = userDao.getCashAccountByLogin(losingBet.getUserLogin());
//                        userCashAcc.setValue(userCashAcc.getValue() - losingBet.getMoney());
//                        userDao.updateCashAccountValue(userCashAcc);
                        appCashAccountDao.updateBalance(losingBet.getMoney());
                        betDao.updateBetStatus(losingBet.getId(),Status.FINISH);
                    }
                }
            }
        }
    }
}
