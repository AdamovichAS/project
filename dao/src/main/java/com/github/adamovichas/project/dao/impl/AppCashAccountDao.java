package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IAppCashAccountDao;
import com.github.adamovichas.project.dao.repository.AppCashAccountRepository;
import com.github.adamovichas.project.entity.AppCashAccountEntity;
import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;

public class AppCashAccountDao implements IAppCashAccountDao {

    private final AppCashAccountRepository repository;

    public AppCashAccountDao(AppCashAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppCashAccountDTO getAppCashAccount() {
        AppCashAccountEntity cashAccountEntity = repository.getOne(1L);
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
    }

    @Override
    public AppCashAccountDTO updateBalance(double change) {
         AppCashAccountEntity accountEntity = repository.getOne(1L);
         accountEntity.setBalance(accountEntity.getBalance() + change);
        return EntityDtoViewConverter.getDTO(accountEntity);
    }
}
