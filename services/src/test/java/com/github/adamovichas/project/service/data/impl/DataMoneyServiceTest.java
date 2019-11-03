package com.github.adamovichas.project.service.data.impl;

import com.github.adamovichas.project.dao.impl.MoneyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class DataMoneyServiceTest {

    @Mock
    public MoneyData dataMoney;

    @InjectMocks
    public DataMoneyService dataMoneyService;

    @BeforeEach
    public void initMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMoney(){
        when(dataMoney.createMoney("test")).thenReturn(true);
        final boolean test = dataMoneyService.createMoney("test");
        assertTrue(test);
    }
}
