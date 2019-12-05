package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.model.dto.BetDTO;
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
    public BetDao dataBet;

    @InjectMocks
    public DataBetService dataBetService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void addBet(){
        BetDTO testBet = new BetDTO("test",100L,1000);
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
        when(dataBet.getAllByUserAndStatus(login)).thenReturn(betViews);
        List<BetView> notFinishedBetByLogin = dataBetService.getNotFinishedBetByLogin(login);
        assertEquals(notFinishedBetByLogin.size(),betViews.size());
    }

//    @Test
//    public void cancelBetById(){
//        dataBetService.cancelBetById(1L);
//        Mockito.verify(dataBet,times(1)).CancelBetById(1L);
//    }
}
