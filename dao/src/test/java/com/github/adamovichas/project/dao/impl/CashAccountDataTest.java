package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.ICashAccountData;
import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})

public class CashAccountDataTest {
    @Autowired
    private ICashAccountData cashAccountData;
    @Autowired
    private IDataUser dataUser;
    private final Util util = new Util();

    @Test
    public void createDepositTrue(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        boolean deposit = cashAccountData.verification(userDTO.getLogin());
        util.deleteDeposit(userDTO.getLogin());
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertTrue(deposit);
    }

    @Test
    public void createDepositFalse(){
        UserDTO userDTO = util.createTestUser();
        boolean deposit = cashAccountData.verification(userDTO.getLogin());
        assertFalse(deposit);
    }

    @Test
    public void getMoneyByLogin(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        cashAccountData.verification(userDTO.getLogin());
        CashAccountDTO moneyByLogin = cashAccountData.getMoneyByLogin(userDTO.getLogin());
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteDeposit(userDTO.getLogin());
        util.deleteTestUser(entity);
        assertEquals(userDTO.getLogin(),moneyByLogin.getLogin());
        assertEquals(0,moneyByLogin.getValue());
    }

}
