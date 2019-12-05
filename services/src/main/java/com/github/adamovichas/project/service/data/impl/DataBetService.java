package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.dao.IBetDao;

import java.util.List;

public class DataBetService implements IDataBetService {

    private static final int PAGE_SIZE = 5;

//    private static volatile IDataBetService instance;

    private final IBetDao data;

    public DataBetService(IBetDao betData) {
        data = betData;
    }
//
//    public static IDataBetService getInstance() {
//        IDataBetService localInstance = instance;
//        if (localInstance == null) {
//            synchronized (IDataBetService.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new DataBetService();
//                }
//            }
//        }
//        return localInstance;
//    }


    @Override
    public Long addBet(BetDTO betDTO) {
        return data.addBet(betDTO);
    }

    @Override
    public BetView getViewById(Long idBet) {
        return data.getViewById(idBet);
    }

    @Override
    public List<BetView> getNotFinishedBetByLogin(String login) {
        return data.getAllByUserAndStatus(login);
    }

    @Override
    public void cancelBetById(List<Long> idBets) {
        for (Long bet : idBets) {
            data.CancelBetById(bet);
        }
    }

    @Override
    public Long getBetMaxPagesByLogin(String login){
        Long countBetsByLogin = data.getCountBetsByLogin(login);
        Long maxPages = countBetsByLogin / PAGE_SIZE;
        if(countBetsByLogin % PAGE_SIZE > 0){
            maxPages++;
        }
        return maxPages;
    }

    @Override
    public List<BetView> getBetsByLoginOnCorrentPage(String login, int page){
        return data.getBetsOnPageByLogin(login,page,PAGE_SIZE);
    }
}
