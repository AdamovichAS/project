package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.CashAccountData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class DataCashAccountServiceTest {

    @Mock
    public CashAccountData dataMoney;

    @InjectMocks
    public DataCashAccountService dataCashAccountService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMoney(){
        when(dataMoney.verification("test")).thenReturn(true);
        final boolean test = dataCashAccountService.verification("test");
        assertTrue(test);
    }
}
