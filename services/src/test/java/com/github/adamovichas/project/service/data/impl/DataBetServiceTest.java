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
import static org.mockito.Mockito.*;

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
        when(userDao.getCashAccountByLogin(testBet.getUserLogin())).thenReturn(userCashAccount);
        when(betDao.addBet(testBet)).thenReturn(testBet.getId());
        betService.addBet(testBet);
        verify(userDao,times(1)).getCashAccountByLogin(testBet.getUserLogin());
        verify(userDao,times(1)).updateCashAccountValue(userCashAccount);
        verify(betDao,times(1)).addBet(testBet);

    }

    @Test
    public void getViewById(){
        BetView betView = new BetView();
        when(betDao.getViewById(betView.getId())).thenReturn(betView);
        betService.getViewById(betView.getId());
        verify(betDao,times(1)).getViewById(betView.getId());
    }

    @Test
    public void getNotFinishedBetByLogin(){
        String login = "Test";
        List<BetView>betViews = new ArrayList<>(Arrays.asList(new BetView(),new BetView(), new BetView()));
        when(betDao.getAllByUserAndStatus(login, Status.FINISH)).thenReturn(betViews);
        List<BetView> notFinishedBetByLogin = betService.getAllByUserAndStatus(login,Status.FINISH);
        assertEquals(notFinishedBetByLogin.size(),betViews.size());
    }

    @Test
    public void cancelBetById(){
        BetView betView = new BetView();
        betView.setLogin("test");
        betView.setMoney(100);
        CashAccountDTO cashAccountDTO = createUserCashAccount();
        List<Long>listId = new ArrayList<>(Arrays.asList(1L,2L));
        when(betDao.getViewById(anyLong())).thenReturn(betView);
        when(userDao.getCashAccountByLogin(anyString())).thenReturn(cashAccountDTO);
        betService.cancelBetById(listId);
        verify(betDao,times(listId.size())).getViewById(anyLong());
        verify(userDao,times(listId.size())).getCashAccountByLogin(anyString());
        verify(betDao,times(listId.size())).updateBetStatus(anyLong(),any(Status.class));
        verify(userDao,times(listId.size())).updateCashAccountValue(any(CashAccountDTO.class));
    }

    @Test
    void getBetMaxPagesByLoginAndStatus(){
        when(betDao.getCountBetsByLoginAbdStatus(anyString(),any(Status.class))).thenReturn(10L);
        final Long pages = betService.getBetMaxPagesByLoginAndStatus("test", Status.FINISH);
        verify(betDao,times(1)).getCountBetsByLoginAbdStatus(anyString(),any(Status.class));
        assertEquals(pages,2);
    }


}
