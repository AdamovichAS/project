package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IAppCashAccountDao;
import com.github.adamovichas.project.dao.repository.AppCashAccountRepository;
import com.github.adamovichas.project.entity.AppCashAccountEntity;
import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AppCashAccountDao implements IAppCashAccountDao {

    private final AppCashAccountRepository repository;

    public AppCashAccountDao(AppCashAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public AppCashAccountDTO getAppCashAccount() {
        AppCashAccountEntity cashAccountEntity = repository.getOne(1L);
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public AppCashAccountDTO updateBalance(double change) {
         AppCashAccountEntity accountEntity = repository.getOne(1L);
         accountEntity.setBalance(accountEntity.getBalance() + change);
         repository.flush();
        return EntityDtoViewConverter.getDTO(accountEntity);
    }
}
