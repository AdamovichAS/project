package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.dao.IAppCashAccountDao;
import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional()
//@Rollback(value = false)
public class AppCashAccountDaoTest {

    @Autowired
    private IAppCashAccountDao appCashAccountDao;

    @Autowired
    private IUtil util;

    @Test
    public void updateBalanceTest(){
        AppCashAccountDTO appCashAccount = util.createAppCashAccount();
        AppCashAccountDTO accountAfterUpdate = appCashAccountDao.updateBalance(appCashAccount);
        assertEquals(accountAfterUpdate.getBalance(),appCashAccount.getBalance());
    }
}
