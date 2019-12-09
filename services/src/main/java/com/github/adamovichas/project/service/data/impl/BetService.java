package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.dao.IBetDao;

import java.util.List;

public class BetService implements IBetService {

    private static final int PAGE_SIZE = 5;

    private final IBetDao betDao;
    private final IUserDao userDao;

    public BetService(IBetDao betDao, IUserDao userDao) {
        this.betDao = betDao;
        this.userDao = userDao;

    }

    @Override
    public Long addBet(BetDTO betDTO) {
        CashAccountDTO userCashAcc = userDao.getCashAccountByLogin(betDTO.getUserLogin());
        userCashAcc.setValue(userCashAcc.getValue() - betDTO.getMoney());
        userDao.updateCashAccountValue(userCashAcc);
        return betDao.addBet(betDTO);
    }

    @Override
    public BetView getViewById(Long idBet) {
        return betDao.getViewById(idBet);
    }

    @Override
    public List<BetView> getAllByUserAndStatus(String login, Status status) {
        return betDao.getAllByUserAndStatus(login, status);
    }

    @Override
    public void cancelBetById(List<Long> idBets) {
        for (Long bet : idBets) {
            BetView betView = betDao.getViewById(bet);
            CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(betView.getLogin());
            betDao.updateBetStatus(bet,Status.CANCELD);
            cashAccountDTO.setValue(cashAccountDTO.getValue() + betView.getMoney());
            userDao.updateCashAccountValue(cashAccountDTO);
        }
    }



    @Override
    public Long getBetMaxPagesByLoginAndStatus(String login, Status status){
        Long countBetsByLogin = betDao.getCountBetsByLoginAbdStatus(login,status);
        Long maxPages = countBetsByLogin / PAGE_SIZE;
        if(countBetsByLogin % PAGE_SIZE > 0){
            maxPages++;
        }
        return maxPages;
    }

    @Override
    public List<BetView> getBetsByLoginOnCurrentPage(String login, Status status, int page){
        return betDao.getBetsOnPageByLoginAndStatus(login,status,page,PAGE_SIZE);
    }
}
