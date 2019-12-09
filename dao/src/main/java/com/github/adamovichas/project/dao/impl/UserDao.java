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

import java.util.Optional;

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
        Optional<UserEntity> entityOptional = userRepository.findById(login);
        if(!entityOptional.isPresent()){
            return null;
        }
        UserEntity userEntity = entityOptional.get();
        return EntityDtoViewConverter.getDTO(userEntity);
    }

    @Override
    public void blockUser(String login) {
        UserEntity userEntity = userRepository.getOne(login);
        userEntity.setRole(Role.BLOCKED);
    }

    @Override
    public boolean updateUser(UserDTO user) {
        Optional<UserEntity> optional = userRepository.findById(user.getLogin());
        if(!optional.isPresent()){
            return false;
        }
        final UserEntity userEntity = optional.get();
        userEntity.setRole(user.getRole());
        userEntity.setPassword(user.getPassword());
        return true;
    }

    /**
     *CashAccount
     * @return
     */

    @Override
    public CashAccountDTO addUserCashAccount(String login) {
        CashAccountEntity cashAccountEntity = new CashAccountEntity();
        cashAccountEntity.setValue(0);
        UserEntity userEntity = userRepository.getOne(login);
        cashAccountEntity.setUserEntity(userEntity);
        userEntity.setCashAccount(cashAccountEntity);
        cashAccountRepository.save(cashAccountEntity);
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
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
     * @return
     */

    @Override
    public UserPassportDTO addPassport(UserPassportDTO userPassport) {
        UserPassportEntity passportEntity = EntityDtoViewConverter.getEntity(userPassport);
        UserEntity userEntity = userRepository.getOne(userPassport.getUserLogin());
        passportEntity.setUserEntity(userEntity);
        userEntity.setUserPassportEntity(passportEntity);
        passportRepository.save(passportEntity);
        return EntityDtoViewConverter.getDTO(passportEntity);
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
        Optional<UserPassportEntity> optional = passportRepository.findById(login);
        if(!optional.isPresent()){
            return null;
        }
        UserPassportEntity passportEntity = optional.get();
        return EntityDtoViewConverter.getDTO(passportEntity);
    }

}
