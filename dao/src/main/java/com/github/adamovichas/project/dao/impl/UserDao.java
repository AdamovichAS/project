package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.dao.repository.UserCashAccountRepository;
import com.github.adamovichas.project.dao.repository.UserPassportRepository;
import com.github.adamovichas.project.dao.repository.UserRepository;
import com.github.adamovichas.project.entity.CashAccountEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.entity.UserPassportEntity;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;

public class UserDao implements IUserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final UserRepository userRepository;
    private final UserPassportRepository passportRepository;
    private final UserCashAccountRepository cashAccountRepository;

    public UserDao(UserRepository userRepository, UserPassportRepository passportRepository, UserCashAccountRepository cashAccountRepository) {
        this.userRepository = userRepository;
        this.passportRepository = passportRepository;
        this.cashAccountRepository = cashAccountRepository;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        UserEntity userEntity = EntityDtoViewConverter.getEntity(userDTO);
        userRepository.save(userEntity);
    }


    @Override
    public UserDTO getUserByLogin(String login) {
        UserEntity userEntity = userRepository.findById(login).get();
        return EntityDtoViewConverter.getDTO(userEntity);
    }

    @Override
    public void block(String login) {
        final UserEntity userEntity = userRepository.findById(login).get();
        userEntity.setRole(Role.BLOCKED);
    }

    @Override
    public boolean updateUser(UserDTO user) {
        boolean result = false;
        UserEntity userEntity = userRepository.findById(user.getLogin()).get();
        userEntity.setRole(user.getRole());
        userEntity.setPassword(user.getPassword());
        result = true;
        return result;
    }

    /**
     *CashAccount
     */

    @Override
    public void addUserCashAccount(String login) {
        CashAccountEntity cashAccountEntity = new CashAccountEntity();
        cashAccountEntity.setValue(0);
        UserEntity userEntity = userRepository.getOne(login);
        cashAccountEntity.setUserEntity(userEntity);
        userEntity.setCashAccount(cashAccountEntity);
        cashAccountRepository.save(cashAccountEntity);
    }

    @Override
    public CashAccountDTO getCashAccountByLogin(String login) {
        CashAccountEntity cashAccountEntity = cashAccountRepository.getOne(login);
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
    }

    @Override
    @Modifying(flushAutomatically = true)
    public CashAccountDTO updateCashAccountValue(CashAccountDTO cashAccountDTO) {
        CashAccountEntity cashAccountEntity = cashAccountRepository.getOne(cashAccountDTO.getLogin());
        cashAccountEntity.setValue(cashAccountDTO.getValue());
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
    }

    /**
     * UserPassport
     */

    @Override
    public void addPassport(UserPassportDTO userPassport) {
        UserPassportEntity passportEntity = EntityDtoViewConverter.getEntity(userPassport);
        UserEntity userEntity = userRepository.getOne(userPassport.getUserLogin());
        passportEntity.setUserEntity(userEntity);
        userEntity.setUserPassportEntity(passportEntity);
        passportRepository.save(passportEntity);
    }

    @Override
    public UserPassportDTO updatePassport(UserPassportDTO userPassport) {
        UserPassportEntity passportEntity = passportRepository.getOne(userPassport.getUserLogin());
        passportEntity.setFirstName(userPassport.getFirstName());
        passportEntity.setLastName(userPassport.getLastName());
        passportEntity.setPassSeries(userPassport.getPassSeries());
        return EntityDtoViewConverter.getDTO(passportEntity);
    }

    @Override
    public UserPassportDTO getPassport(String login) {
        UserPassportEntity passportEntity = passportRepository.getOne(login);
        return EntityDtoViewConverter.getDTO(passportEntity);
    }

}
