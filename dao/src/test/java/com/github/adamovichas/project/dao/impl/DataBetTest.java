package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataBetTest {

    private BetData betData = (BetData) BetData.getInstance();
    private DataUser dataUser = (DataUser) DataUser.getInstance();
    private UtilTest util = UtilTest.UTIL_TEST;

    @AfterAll
    public static void cleanUp() {
        HibernateUtil.closeEMFactory();
    }

    @Test
    public void addBet(){
        UserDTO userDTO = util.createTestUser();
        BetDTO betDTO = util.createFinishedBet();
        dataUser.addUser(userDTO);
        Long idBet = betData.addBet(betDTO);
        betData.CancelBetById(idBet);
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertNotNull(idBet);
    }

    @Test
    public void getViewById(){
        UserDTO userDTO = util.createTestUser();
        BetDTO betDTO = util.createFinishedBet();
        dataUser.addUser(userDTO);
        Long idBet = betData.addBet(betDTO);
        BetView viewById = betData.getViewById(idBet);
        betData.CancelBetById(idBet);
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertEquals(viewById.getLogin(),betDTO.getUserLogin());
        assertEquals(viewById.getMoney(),betDTO.getMoney());
        assertEquals(viewById.getFactor().getId(),betDTO.getFactorId());
        assertNotNull(viewById.getId());
    }

    @Test
    public void getNotFinishedBetByLoginEmpty(){
        UserDTO userDTO = util.createTestUser();
        BetDTO betDTO = util.createFinishedBet();
        dataUser.addUser(userDTO);
        Long idBet = betData.addBet(betDTO);
        List<BetView> views = betData.getNotFinishedBetByLogin(userDTO.getLogin());
        betData.CancelBetById(idBet);
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertEquals(views.size(),0);
    }

    @Test
    public void getNotFinishedBetByLogin(){
        UserDTO userDTO = util.createTestUser();
        BetDTO finishedBet = util.createFinishedBet();
        BetDTO notFinishedBet = util.createNotFinishedBet();
        dataUser.addUser(userDTO);
        Long idBet1 = betData.addBet(finishedBet);
        Long idBet2 = betData.addBet(notFinishedBet);
        List<BetView> views = betData.getNotFinishedBetByLogin(userDTO.getLogin());
        betData.CancelBetById(idBet1);
        betData.CancelBetById(idBet2);
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertEquals(views.size(),1);
        for (BetView view : views) {
            assertEquals(view.getLogin(),notFinishedBet.getUserLogin());
            assertEquals(view.getMoney(),notFinishedBet.getMoney());
            assertEquals(view.getFactor().getId(),notFinishedBet.getFactorId());
            assertNotNull(view.getId());
        }
    }
}
