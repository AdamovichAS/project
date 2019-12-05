package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.ICashAccountDao;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional()
//@Rollback(value = false)
public class CashAccountDaoTest {
    @Autowired
    private ICashAccountDao cashAccountData;
    @Autowired
    private IUserDao dataUser;
    @Autowired
    private IUtil util;

    @Test
    public void createAccountTrue(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        boolean deposit = cashAccountData.create(userDTO.getLogin());
        assertTrue(deposit);
    }

//    @Test
//    public void createAccountFalse(){
//        UserDTO userDTO = util.createTestUser();
//        boolean deposit = cashAccountData.create(userDTO.getLogin());
//        assertFalse(deposit);
//    }

    @Test
    public void getAccountByLogin(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        cashAccountData.create(userDTO.getLogin());
        CashAccountDTO moneyByLogin = cashAccountData.getCashAccountByLogin(userDTO.getLogin());
        assertEquals(userDTO.getLogin(),moneyByLogin.getLogin());
        assertEquals(0,moneyByLogin.getValue());
    }

    @Test
    public void updateCashAccountValue(){
        double newCashAccountValue = 100;
        UserDTO testUser = util.createTestUser();
        dataUser.addUser(testUser);
        cashAccountData.create(testUser.getLogin());
        CashAccountDTO account = cashAccountData.getCashAccountByLogin(testUser.getLogin());
        assertEquals(account.getValue(),0);
        account.setValue(newCashAccountValue);
        boolean isUpdate = cashAccountData.updateCashAccountValue(account);
        assertTrue(isUpdate);
    }

}
