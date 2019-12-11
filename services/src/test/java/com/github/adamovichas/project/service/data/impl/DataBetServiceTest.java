package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.UserDao;
import com.github.adamovichas.project.dao.repository.UserCashAccountRepository;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.dao.impl.BetDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DataBetServiceTest {

    @Mock
    public BetDao betDao;

    @Mock
    public UserDao userDao;

    @InjectMocks
    public BetService betService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }


    private CashAccountDTO createUserCashAccount(){
        CashAccountDTO cashAccountDTO = new CashAccountDTO();
        cashAccountDTO.setLogin("test");
        cashAccountDTO.setValue(100);
        return cashAccountDTO;
    }

    @Test
    public void addBet(){
        BetDTO testBet = new BetDTO("test",100L,1000);
        CashAccountDTO userCashAccount = createUserCashAccount();
        testBet.setId(50L);
        when(betDao.addBet(testBet)).thenReturn(testBet.getId());
        when(userDao.getCashAccountByLogin(testBet.getUserLogin())).thenReturn(userCashAccount);
        Long id = betService.addBet(testBet);
        assertEquals(id,testBet.getId());
    }

    @Test
    public void getViewById(){
        BetView betView = new BetView();
        betView.setId(100L);
        betView.setLogin("Test");
        when(betDao.getViewById(betView.getId())).thenReturn(betView);
        BetView viewById = betService.getViewById(betView.getId());
        assertEquals(viewById.getLogin(),betView.getLogin());
    }

    @Test
    public void getNotFinishedBetByLogin(){
        String login = "Test";
        List<BetView>betViews = new ArrayList<>(Arrays.asList(new BetView(),new BetView(), new BetView()));
        when(betDao.getAllByUserAndStatus(login, Status.FINISH)).thenReturn(betViews);
        List<BetView> notFinishedBetByLogin = betService.getAllByUserAndStatus(login,Status.FINISH);
        assertEquals(notFinishedBetByLogin.size(),betViews.size());
    }

//    @Test
//    public void cancelBetById(){
//        betService.cancelBetById(1L);
//        Mockito.verify(betDao,times(1)).updateBetStatus(1L);
//    }
}
