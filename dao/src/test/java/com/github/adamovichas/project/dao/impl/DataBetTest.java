package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IBetDao;
import com.github.adamovichas.project.dao.ICashAccountDao;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.view.BetView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class,Util.class})
@Transactional()
//@Rollback(value = false)
public class DataBetTest {
    @Autowired
    private IBetDao betData;
    @Autowired
    private IUserDao dataUser;
    @Autowired
    private ICashAccountDao cashAccountData;
    @Autowired
    private IUtil util;

    @Test
    public void addBet(){
        UserDTO userDTO = util.createTestUser();
        BetDTO betDTO = util.createFinishedBet();
        dataUser.addUser(userDTO);
        cashAccountData.addUserCashAccount(userDTO.getLogin());
        Long idBet = betData.addBet(betDTO);
        assertNotNull(idBet);
    }

    @Test
    public void getViewById(){
        UserDTO userDTO = util.createTestUser();
        BetDTO betDTO = util.createFinishedBet();
        dataUser.addUser(userDTO);
        cashAccountData.addUserCashAccount(userDTO.getLogin());
        Long idBet = betData.addBet(betDTO);
        BetView viewById = betData.getViewById(idBet);
        betData.CancelBetById(idBet);
        assertEquals(viewById.getLogin(),betDTO.getUserLogin());
        assertEquals(viewById.getMoney(),betDTO.getMoney());
        assertEquals(viewById.getFactor().getId(),betDTO.getFactorId());
        assertNotNull(viewById.getId());
    }

    @Test
    public void getAllByUserLoginAndStatusEmpty(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        cashAccountData.addUserCashAccount(userDTO.getLogin());
        List<BetView> views = betData.getAllByUserAndStatus(userDTO.getLogin(),Status.FINISH);
        assertEquals(views.size(),0);
        views = betData.getAllByUserAndStatus(userDTO.getLogin(),Status.CANCELD);
        assertEquals(views.size(),0);
        views = betData.getAllByUserAndStatus(userDTO.getLogin(),Status.RUN_TIME);
        assertEquals(views.size(),0);
    }

    @Test
    public void getAllByUserLoginAndStatus(){
        UserDTO userDTO = util.createTestUser();
        BetDTO finishedBet = util.createFinishedBet();
        BetDTO notFinishedBet = util.createNotFinishedBet();
        BetDTO canselBet = util.createCanselBet();
        dataUser.addUser(userDTO);
        cashAccountData.addUserCashAccount(userDTO.getLogin());
        betData.addBet(finishedBet);
        betData.addBet(notFinishedBet);
        betData.addBet(canselBet);
        List<Status>statuses = Arrays.asList(Status.CANCELD,Status.FINISH,Status.RUN_TIME);
        for (Status status : statuses) {
            List<BetView> views = betData.getAllByUserAndStatus(userDTO.getLogin(),status);
            assertEquals(views.size(),1);
            for (BetView view : views) {
                assertEquals(view.getLogin(),notFinishedBet.getUserLogin());
                assertEquals(view.getMoney(),notFinishedBet.getMoney());
                assertEquals(view.getFactor().getId(),notFinishedBet.getFactorId());
                assertNotNull(view.getId());
                assertEquals(view.getStatus(),status);
            }
        }
    }

    @Test
    public void CancelBetById(){
        UserDTO userDTO = util.createTestUser();
        BetDTO notFinishedBet = util.createNotFinishedBet();
        dataUser.addUser(userDTO);
        cashAccountData.addUserCashAccount(userDTO.getLogin());
        Long idBet = betData.addBet(notFinishedBet);
        betData.CancelBetById(idBet);
        BetView view = betData.getViewById(idBet);
        assertEquals(view.getStatus(), Status.CANCELD);
    }
}
