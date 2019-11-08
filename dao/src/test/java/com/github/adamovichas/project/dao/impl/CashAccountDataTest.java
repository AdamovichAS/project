package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class CashAccountDataTest {
    private CashAccountData cashAccountData = (CashAccountData) CashAccountData.getInstance();
    private DataUser dataUser = (DataUser) DataUser.getInstance();
    private Util util = Util.UTIL_TEST;
    private static EntityManager entityManager;

    @BeforeAll
    static void init() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @AfterAll
    public static void cleanUp() {
        entityManager.close();
    }

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
