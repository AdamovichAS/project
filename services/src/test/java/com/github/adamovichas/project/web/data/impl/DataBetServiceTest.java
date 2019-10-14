package com.github.adamovichas.project.web.data.impl;

import com.github.adamovichas.project.web.dto.Bet;
import com.github.adamovichas.project.web.dto.BetView;
import com.github.adamovichas.project.web.dto.Money;
import com.github.adamovichas.project.project.dao.impl.BetData;
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
    public BetData dataBet;

    @InjectMocks
    public DataBetService dataBetService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getMoneyByLogin(){
        Money money = new Money("Test",1000);
        when(dataBet.getMoneyByLogin("Test")).thenReturn(money);
        Money test = dataBetService.getMoneyByLogin("Test");
        assertEquals(test.getValue(),money.getValue());
    }

    @Test
    public void addBet(){
        Bet testBet = new Bet("test",100L,1000);
        testBet.setId(50L);
        when(dataBet.addBet(testBet)).thenReturn(testBet.getId());
        Long id = dataBetService.addBet(testBet);
        assertEquals(id,testBet.getId());
    }

    @Test
    public void getViewById(){
        BetView betView = new BetView();
        betView.setId(100L);
        betView.setLogin("Test");
        when(dataBet.getViewById(betView.getId())).thenReturn(betView);
        BetView viewById = dataBetService.getViewById(betView.getId());
        assertEquals(viewById.getLogin(),betView.getLogin());
    }

    @Test
    public void getNotFinishedBetByLogin(){
        String login = "Test";
        List<BetView>betViews = new ArrayList<>(Arrays.asList(new BetView(),new BetView(), new BetView()));
        when(dataBet.getNotFinishedBetByLogin(login)).thenReturn(betViews);
        List<BetView> notFinishedBetByLogin = dataBetService.getNotFinishedBetByLogin(login);
        assertEquals(notFinishedBetByLogin.size(),betViews.size());
    }
}